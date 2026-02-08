package org.dominokit.cli.generator;

/**
 * Folder that maps a Java package name to a path.
 */
public class Package extends Folder {

    /**
     * Creates a package folder from a dot-separated package name.
     *
     * @param name package name
     */
    public Package(String name) {
        super(name.replaceAll("\\.", "/"));
    }
}
