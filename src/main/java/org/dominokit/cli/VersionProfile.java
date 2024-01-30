package org.dominokit.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VersionProfile {

    private static VersionProfile INSTANCE;

    private final String version;
    private final String templatesPath;
    private final List<ToolVersion> toolsVersions = new ArrayList<>();

    public VersionProfile(String version, String templatesPath, ToolVersion... toolVersions) {
        this.version = version;
        this.templatesPath = templatesPath;
        this.toolsVersions.addAll(Arrays.asList(toolVersions));
    }

    public String getVersion() {
        return version;
    }

    public String getTemplatesPath() {
        return templatesPath;
    }

    public List<ToolVersion> getToolsVersions() {
        return toolsVersions;
    }

    public static void setVersion(String version) {
        INSTANCE = get(version);
    }

    public static VersionProfile get() {
        return INSTANCE;
    }

    private static VersionProfile get(String version) {
        switch (version) {
            case "v1":
                return new VersionProfile(version, version,
                        ToolVersion.of("domino_ui_version", "1.0.6"),
                        ToolVersion.of("domino_history_version", "1.0.1"),
                        ToolVersion.of("domino_mvp_version", "1.0.0"),
                        ToolVersion.of("domino_rest_version", "1.0.1"),
                        ToolVersion.of("domino_jackson_version", "1.0.4"),
                        ToolVersion.of("quarkus_version", "2.16.7.Final"),
                        ToolVersion.of("vertx_version", "3.9.4"),
                        ToolVersion.of("gwt_version", "2.10.0"),
                        ToolVersion.of("j2cl_maven_plugin_version", "0.21.0")
                );
            case "v2":
                return new VersionProfile(version, version,
                        ToolVersion.of("domino_ui_version", "2.0.0"),
                        ToolVersion.of("domino_history_version", "1.0.3"),
                        ToolVersion.of("domino_mvp_version", "2.0.0-RC2"),
                        ToolVersion.of("domino_rest_version", "2.0.0-RC1"),
                        ToolVersion.of("domino_jackson_version", "1.0.4"),
                        ToolVersion.of("quarkus_version", "3.6.5"),
                        ToolVersion.of("vertx_version", "3.9.4"),
                        ToolVersion.of("gwt_version", "2.11.0"),
                        ToolVersion.of("j2cl_maven_plugin_version", "0.22.0")
                );
            case "dev":
                return new VersionProfile(version, "v2",
                        ToolVersion.of("domino_ui_version", "HEAD-SNAPSHOT"),
                        ToolVersion.of("domino_history_version", "HEAD-SNAPSHOT"),
                        ToolVersion.of("domino_mvp_version", "HEAD-SNAPSHOT"),
                        ToolVersion.of("domino_rest_version", "HEAD-SNAPSHOT"),
                        ToolVersion.of("domino_jackson_version", "HEAD-SNAPSHOT"),
                        ToolVersion.of("quarkus_version", "3.6.5"),
                        ToolVersion.of("vertx_version", "3.9.4"),
                        ToolVersion.of("gwt_version", "2.11.0"),
                        ToolVersion.of("j2cl_maven_plugin_version", "0.22.0")
                );

        }
        throw new IllegalArgumentException("Invalid versions profile [" + version + "] use one of [v1, v2, dev].");
    }
}
