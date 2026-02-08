package org.dominokit.cli;

import java.util.List;

/**
 * Holds versioned tool dependencies and template locations for generation.
 */
public class VersionProfile {

    private static VersionProfile INSTANCE;

    private final String version;
    private final String templatesPath;
    private final VersionsProfile versionsProfile;

    /**
     * Creates a version profile definition.
     *
     * @param version profile name
     * @param templatesPath templates root folder
     * @param versionsProfile tool version mappings provider
     */
    public VersionProfile(String version, String templatesPath, VersionsProfile versionsProfile) {
        this.version = version;
        this.templatesPath = templatesPath;
        this.versionsProfile = versionsProfile;
    }

    /**
     * Returns the profile name.
     *
     * @return profile name
     */
    public String getVersion() {
        return version;
    }

    /**
     * Returns the templates path for this profile.
     *
     * @return templates path
     */
    public String getTemplatesPath() {
        return templatesPath;
    }

    /**
     * Returns the tool version mappings for this profile.
     *
     * @return list of tool versions
     */
    public List<ToolVersion> getToolsVersions() {
        return versionsProfile.getVersions();
    }

    /**
     * Sets the active version profile.
     *
     * @param version profile name
     */
    public static void setVersion(String version) {
        INSTANCE = get(version);
    }

    /**
     * Returns the currently active profile.
     *
     * @return current profile
     */
    public static VersionProfile get() {
        return INSTANCE;
    }

    private static VersionProfile get(String version) {
        switch (version) {
            case "release":
                return new VersionProfile(version, "", new ReleaseVersionsProfile());
            case "dev":
                return new VersionProfile(version, "", new DevVersionsProfile());

        }
        throw new IllegalArgumentException("Invalid versions profile [" + version + "] use one of [release, dev].");
    }
}
