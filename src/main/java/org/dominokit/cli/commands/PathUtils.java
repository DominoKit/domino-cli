package org.dominokit.cli.commands;

import static java.util.Objects.nonNull;

/**
 * Utilities for resolving the CLI working directory.
 */
public class PathUtils {
    private static String workingDir;

    /**
     * Returns the working directory for generated output.
     *
     * @return user directory or overridden working directory
     */
    public static String getUserDir() {
        if (nonNull(workingDir)) {
            return workingDir;
        } else {
            return System.getProperty("user.dir");
        }
    }

    /**
     * Overrides the working directory used by generators.
     *
     * @param workingDir absolute path to use for generation
     */
    public static void setWorkingDir(String workingDir) {
        PathUtils.workingDir = workingDir;
    }
}
