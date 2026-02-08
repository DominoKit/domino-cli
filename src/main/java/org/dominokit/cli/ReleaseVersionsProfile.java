package org.dominokit.cli;

import java.util.List;

/**
 * Released tool versions profile.
 */
public class ReleaseVersionsProfile implements VersionsProfile {

    private static final List<ToolVersion> DEFAULT_VERSIONS = List.of(
            ToolVersion.of("domino_ui_version", "org.dominokit", "domino-ui", "2.0.4"),
            ToolVersion.of("domino_history_version", "org.dominokit", "domino-history", "1.0.4"),
            ToolVersion.of("domino_rest_version", "org.dominokit", "domino-rest", "2.0.0-RC2"),
            ToolVersion.of("domino_jackson_version", "org.dominokit", "domino-jackson", "1.0.4"),
            ToolVersion.of("domino_auto_version", "org.dominokit", "domino-auto", "1.0.2"),
            ToolVersion.of("domino_brix_version", "org.dominokit", "domino-brix", "1.0.0-RC1"),
            ToolVersion.of("quarkus_version", "io.quarkus", "quarkus-universe-bom", "3.6.5"),
            ToolVersion.of("vertx_version", "io.vertx", "vertx-core", "3.9.4"),
            ToolVersion.of("gwt_version", "org.gwtproject", "gwt", "2.12.2"),
            ToolVersion.of("dagger_version", "com.google.dagger", "dagger", "2.51.1")
    );

    private final List<ToolVersion> versions;

    public ReleaseVersionsProfile() {
        this.versions = new VersionUpdateService().applyReleaseVersions(DEFAULT_VERSIONS);
    }

    public static List<ToolVersion> defaultVersions() {
        return DEFAULT_VERSIONS;
    }

    @Override
    public List<ToolVersion> getVersions() {
        return versions;
    }
}
