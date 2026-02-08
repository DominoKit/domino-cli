package org.dominokit.cli;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Persists cached version mappings across runs.
 */
public class VersionCache {

    private static final String CACHE_DIR = ".domino-cli";
    private static final String CACHE_FILE = "versions.json";
    private static final Pattern ENTRY_PATTERN = Pattern.compile("\"([^\"]+)\"\\s*:\\s*\"([^\"]*)\"");

    public CacheResult load() {
        Path cachePath = cachePath();
        if (!Files.exists(cachePath)) {
            return new CacheResult(false, new HashMap<>());
        }
        try {
            String content = Files.readString(cachePath, StandardCharsets.UTF_8);
            Map<String, String> versions = new HashMap<>();
            Matcher matcher = ENTRY_PATTERN.matcher(content);
            while (matcher.find()) {
                versions.put(unescape(matcher.group(1)), unescape(matcher.group(2)));
            }
            return new CacheResult(true, versions);
        } catch (IOException e) {
            return new CacheResult(true, new HashMap<>());
        }
    }

    public void save(Map<String, String> versions) {
        Path cachePath = cachePath();
        try {
            Files.createDirectories(cachePath.getParent());
            StringBuilder builder = new StringBuilder();
            builder.append("{");
            boolean first = true;
            for (Map.Entry<String, String> entry : versions.entrySet()) {
                if (!first) {
                    builder.append(",");
                }
                first = false;
                builder.append("\"")
                        .append(escape(entry.getKey()))
                        .append("\":\"")
                        .append(escape(entry.getValue()))
                        .append("\"");
            }
            builder.append("}");
            Files.writeString(cachePath, builder.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // Best-effort cache; ignore errors to keep generation flowing.
        }
    }

    private Path cachePath() {
        return Paths.get(System.getProperty("user.home"), CACHE_DIR, CACHE_FILE);
    }

    private String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private String unescape(String value) {
        return value.replace("\\\"", "\"").replace("\\\\", "\\");
    }

    public static class CacheResult {
        private final boolean exists;
        private final Map<String, String> versions;

        public CacheResult(boolean exists, Map<String, String> versions) {
            this.exists = exists;
            this.versions = versions;
        }

        public boolean exists() {
            return exists;
        }

        public Map<String, String> versions() {
            return versions;
        }
    }
}
