package org.dominokit.cli.commands;

import static java.util.Objects.nonNull;

public class PathUtils {


    private static String workingDir;

    public static String getUserDir() {
        if (nonNull(workingDir)) {
            return workingDir;
        } else {
            return System.getProperty("user.dir");
        }
    }

    static void setWorkingDir(String workingDir) {
        PathUtils.workingDir = workingDir;
    }
}
