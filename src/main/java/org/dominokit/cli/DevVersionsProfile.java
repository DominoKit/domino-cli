package org.dominokit.cli;

import java.util.List;

/**
 * Development (HEAD-SNAPSHOT) tool versions profile.
 */
public class DevVersionsProfile implements VersionsProfile {

    private static final List<ToolVersion> VERSIONS = List.of(
            ToolVersion.of("domino_ui_version", "org.dominokit", "domino-ui", "HEAD-SNAPSHOT"),
            ToolVersion.of("domino_history_version", "org.dominokit", "domino-history", "HEAD-SNAPSHOT"),
            ToolVersion.of("domino_rest_version", "org.dominokit", "domino-rest", "HEAD-SNAPSHOT"),
            ToolVersion.of("domino_jackson_version", "org.dominokit", "domino-jackson", "HEAD-SNAPSHOT"),
            ToolVersion.of("domino_auto_version", "org.dominokit", "domino-auto", "1.0.2"),
            ToolVersion.of("domino_brix_version", "org.dominokit", "domino-brix", "HEAD-SNAPSHOT"),
            ToolVersion.of("quarkus_version", "io.quarkus", "quarkus-universe-bom", "3.6.5"),
            ToolVersion.of("vertx_version", "io.vertx", "vertx-core", "3.9.4"),
            ToolVersion.of("gwt_version", "org.gwtproject", "gwt", "2.12.2"),
            ToolVersion.of("dagger_version", "com.google.dagger", "dagger", "2.51.1")
    );

    @Override
    public List<ToolVersion> getVersions() {
        return VERSIONS;
    }
}
