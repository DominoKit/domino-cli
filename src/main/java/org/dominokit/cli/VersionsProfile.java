package org.dominokit.cli;

import java.util.List;

/**
 * Provides tool versions for template rendering.
 */
public interface VersionsProfile {

    /**
     * Returns tool version mappings for a profile.
     *
     * @return tool versions list
     */
    List<ToolVersion> getVersions();
}
