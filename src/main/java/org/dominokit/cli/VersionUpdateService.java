package org.dominokit.cli;

import org.apache.maven.artifact.versioning.ComparableVersion;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

/**
 * Checks for newer release versions and updates cached values when approved.
 */
public class VersionUpdateService {

    private static final Set<String> AUTO_UPDATE_GROUPS = Set.of(
            "org.dominokit",
            "org.gwtproject"
    );
    private static final String MAVEN_SEARCH_URL = "https://search.maven.org/solrsearch/select";
    private static final List<String> MAVEN_METADATA_REPOS = List.of(
            "https://repo1.maven.org/maven2",
            "https://repo.maven.apache.org/maven2"
    );

    public List<ToolVersion> applyReleaseVersions(List<ToolVersion> defaults) {
        return applyReleaseVersions(defaults, false);
    }

    public List<ToolVersion> applyReleaseVersions(List<ToolVersion> defaults, boolean forceCheck) {
        VersionCache cache = new VersionCache();
        VersionCache.CacheResult cacheResult = cache.load();

        List<ToolVersion> cachedVersions = applyCache(defaults, cacheResult.versions());
        if (!cacheResult.exists() && !forceCheck) {
            cache.save(cacheMap(cachedVersions));
            return cachedVersions;
        }

        List<UpdateCandidate> updates;
        try {
            updates = findUpdates(cachedVersions);
        } catch (IOException | InterruptedException e) {
            if (!cacheResult.exists()) {
                cache.save(cacheMap(cachedVersions));
            }
            return cachedVersions;
        }

        if (updates.isEmpty()) {
            if (!cacheResult.exists()) {
                cache.save(cacheMap(cachedVersions));
            }
            return cachedVersions;
        }

        if (confirmUpdates(updates)) {
            List<ToolVersion> updated = applyUpdates(cachedVersions, updates);
            cache.save(cacheMap(updated));
            return updated;
        }

        if (!cacheResult.exists()) {
            cache.save(cacheMap(cachedVersions));
        }
        return cachedVersions;
    }

    private List<ToolVersion> applyCache(List<ToolVersion> defaults, Map<String, String> cache) {
        List<ToolVersion> result = new ArrayList<>();
        for (ToolVersion version : defaults) {
            if (shouldAutoUpdate(version)) {
                String cachedVersion = cache.get(cacheKey(version));
                if (cachedVersion != null && !cachedVersion.isBlank()) {
                    result.add(version.withVersion(cachedVersion));
                    continue;
                }
            }
            result.add(version);
        }
        return result;
    }

    private Map<String, String> cacheMap(List<ToolVersion> versions) {
        Map<String, String> map = new HashMap<>();
        for (ToolVersion version : versions) {
            if (shouldAutoUpdate(version)) {
                map.put(cacheKey(version), version.getVersion());
            }
        }
        return map;
    }

    private List<UpdateCandidate> findUpdates(List<ToolVersion> versions) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        List<UpdateCandidate> updates = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (ToolVersion version : versions) {
            if (!shouldAutoUpdate(version)) {
                continue;
            }
            String key = cacheKey(version);
            if (!seen.add(key)) {
                continue;
            }
            Optional<String> latest = fetchLatestVersion(client, version.getGroupId(), version.getArtifactId());
            if (latest.isEmpty()) {
                continue;
            }
            String latestVersion = latest.get();
            if (isNewer(version.getVersion(), latestVersion)) {
                updates.add(new UpdateCandidate(version, latestVersion));
            }
        }
        return updates;
    }

    private Optional<String> fetchLatestVersion(HttpClient client, String groupId, String artifactId)
            throws IOException, InterruptedException {
        Optional<String> metadataVersion = fetchLatestVersionFromMetadata(client, groupId, artifactId);
        if (metadataVersion.isPresent()) {
            return metadataVersion;
        }
        String query = "g:\"" + groupId + "\" AND a:\"" + artifactId + "\"";
        String url = MAVEN_SEARCH_URL + "?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&rows=1&wt=json";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            return Optional.empty();
        }
        return extractLatestVersion(response.body());
    }

    private Optional<String> fetchLatestVersionFromMetadata(HttpClient client, String groupId, String artifactId)
            throws IOException, InterruptedException {
        String groupPath = groupId.replace('.', '/');
        for (String repo : MAVEN_METADATA_REPOS) {
            String url = repo + "/" + groupPath + "/" + artifactId + "/maven-metadata.xml";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                continue;
            }
            Optional<String> parsed = extractLatestVersionFromMetadata(response.body());
            if (parsed.isPresent()) {
                return parsed;
            }
        }
        return Optional.empty();
    }

    private Optional<String> extractLatestVersion(String json) {
        String marker = "\"latestVersion\":\"";
        int start = json.indexOf(marker);
        if (start < 0) {
            return Optional.empty();
        }
        int valueStart = start + marker.length();
        int valueEnd = json.indexOf('"', valueStart);
        if (valueEnd < 0) {
            return Optional.empty();
        }
        return Optional.of(json.substring(valueStart, valueEnd));
    }

    private Optional<String> extractLatestVersionFromMetadata(String metadata) {
        String release = extractTagValue(metadata, "release");
        if (isStable(release)) {
            return Optional.of(release);
        }
        String latest = extractTagValue(metadata, "latest");
        if (isStable(latest)) {
            return Optional.of(latest);
        }
        List<String> versions = extractVersions(metadata);
        for (int i = versions.size() - 1; i >= 0; i--) {
            String version = versions.get(i);
            if (isStable(version)) {
                return Optional.of(version);
            }
        }
        return Optional.empty();
    }

    private String extractTagValue(String metadata, String tag) {
        String open = "<" + tag + ">";
        String close = "</" + tag + ">";
        int start = metadata.indexOf(open);
        if (start < 0) {
            return "";
        }
        int valueStart = start + open.length();
        int end = metadata.indexOf(close, valueStart);
        if (end < 0) {
            return "";
        }
        return metadata.substring(valueStart, end).trim();
    }

    private List<String> extractVersions(String metadata) {
        List<String> versions = new ArrayList<>();
        String marker = "<version>";
        String close = "</version>";
        int index = 0;
        while (index >= 0) {
            int start = metadata.indexOf(marker, index);
            if (start < 0) {
                break;
            }
            int valueStart = start + marker.length();
            int end = metadata.indexOf(close, valueStart);
            if (end < 0) {
                break;
            }
            versions.add(metadata.substring(valueStart, end).trim());
            index = end + close.length();
        }
        return versions;
    }

    private boolean isStable(String version) {
        return version != null && !version.isBlank() && !version.contains("SNAPSHOT");
    }

    private boolean isNewer(String current, String latest) {
        ComparableVersion currentVersion = new ComparableVersion(current);
        ComparableVersion latestVersion = new ComparableVersion(latest);
        return latestVersion.compareTo(currentVersion) > 0;
    }

    private boolean confirmUpdates(List<UpdateCandidate> updates) {
        System.out.println("Newer release versions are available:");
        for (UpdateCandidate update : updates) {
            System.out.println("- " + update.coordinate() + " " + update.currentVersion() + " -> " + update.latestVersion());
        }
        String response = readLine("Update cached versions? [y/N]: ");
        return "y".equalsIgnoreCase(response) || "yes".equalsIgnoreCase(response);
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        if (System.console() != null) {
            String line = System.console().readLine();
            return line == null ? "" : line.trim();
        }
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return "";
        }
        String line = scanner.nextLine();
        return line == null ? "" : line.trim();
    }

    private List<ToolVersion> applyUpdates(List<ToolVersion> current, List<UpdateCandidate> updates) {
        Map<String, String> updatesMap = new HashMap<>();
        for (UpdateCandidate update : updates) {
            updatesMap.put(update.key(), update.latestVersion());
        }
        List<ToolVersion> updated = new ArrayList<>();
        for (ToolVersion version : current) {
            if (shouldAutoUpdate(version)) {
                String newVersion = updatesMap.get(cacheKey(version));
                if (newVersion != null) {
                    updated.add(version.withVersion(newVersion));
                    continue;
                }
            }
            updated.add(version);
        }
        return updated;
    }

    private boolean shouldAutoUpdate(ToolVersion version) {
        return AUTO_UPDATE_GROUPS.contains(version.getGroupId());
    }

    private String cacheKey(ToolVersion version) {
        return version.getGroupId() + ":" + version.getArtifactId();
    }

    private static class UpdateCandidate {
        private final ToolVersion version;
        private final String latestVersion;

        private UpdateCandidate(ToolVersion version, String latestVersion) {
            this.version = version;
            this.latestVersion = latestVersion;
        }

        private String key() {
            return version.getGroupId() + ":" + version.getArtifactId();
        }

        private String coordinate() {
            return version.getGroupId() + ":" + version.getArtifactId();
        }

        private String currentVersion() {
            return version.getVersion();
        }

        private String latestVersion() {
            return latestVersion;
        }
    }
}
