package org.dominokit.cli;

/**
 * Pair of template variable key and version value.
 */
public class ToolVersion {

    private final String key;
    private final String groupId;
    private final String artifactId;
    private final String version;

    /**
     * Factory method for a tool version entry.
     *
     * @param key template variable name
     * @param version version value
     * @return tool version instance
     */
    public static ToolVersion of(String key, String groupId, String artifactId, String version){
        return new ToolVersion(key, groupId, artifactId, version);
    }

    private ToolVersion(String key, String groupId, String artifactId, String version) {
        this.key = key;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    /**
     * Returns the template variable key.
     *
     * @return key name
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the maven groupId.
     *
     * @return groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Returns the maven artifactId.
     *
     * @return artifactId
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Returns the tool version value.
     *
     * @return version string
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns a copy with an updated version value.
     *
     * @param version new version value
     * @return new ToolVersion instance
     */
    public ToolVersion withVersion(String version) {
        return new ToolVersion(key, groupId, artifactId, version);
    }
}
