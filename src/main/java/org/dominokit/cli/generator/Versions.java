package org.dominokit.cli.generator;

public enum Versions {
    domino_ui_version("DOMINO_CLI_DOMINO_UI_VERSION","1.0.0-RC19"),
    domino_history_version("DOMINO_CLI_DOMINO_HISTORY_VERSION","1.0.0-RC5"),
    domino_mvp_version("DOMINO_CLI_DOMINO_MVP_VERSION","1.0.0-RC11"),
    domino_rest_version("DOMINO_CLI_DOMINO_REST_VERSION","1.0.0-RC7"),
    domino_jackson_version("DOMINO_CLI_DOMINO_JACKSON_VERSION","1.0.0-RC4"),
    quarkus_version("DOMINO_CLI_QUARKUS_VERSION","2.16.7.Final"),
    vertx_version("DOMINO_CLI_VERTX_VERSION","3.9.0"),
    gwt_version("DOMINO_CLI_GWT_VERSION","2.10.0"),
    j2cl_maven_plugin_version("DOMINO_CLI_J2CL_PLUGIN_VERSION","0.21-SNAPSHOT");

    private String envVariable;
    private String version;

    Versions(String envVariable, String version) {
        this.envVariable = envVariable;
        this.version = version;
    }

    public String get() {
        return version;
    }

    public String getEnvVariable() {
        return envVariable;
    }
}
