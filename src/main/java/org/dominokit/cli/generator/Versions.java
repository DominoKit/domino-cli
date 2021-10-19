package org.dominokit.cli.generator;

public enum Versions {
    domino_ui_version("1.0.0-RC10"),
    domino_history_version("1.0.0-RC4"),
    domino_mvp_version("1.0.0-RC11"),
    domino_rest_version("1.0.0-RC5"),
    domino_jackson_version("1.0.0-RC3"),
    quarkus_version("2.2.3.Final"),
    vertx_version("3.9.0"),
    gwt_version("2.9.0"),
    j2cl_maven_plugin_version("0.18-SNAPSHOT");

    private String version;

    Versions(String version) {
        this.version = version;
    }

    public String get() {
        return version;
    }
}
