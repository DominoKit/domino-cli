package org.dominokit.cli;

public class ToolVersion {

    private final String key;
    private final String version;

    public static ToolVersion of(String key, String version){
        return new ToolVersion(key, version);
    }

    private ToolVersion(String key, String version) {
        this.key = key;
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public String getVersion() {
        return version;
    }
}
