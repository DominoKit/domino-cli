package org.dominokit.cli.generator;

public class Package extends Folder {

    public Package(String name) {
        super(name.replaceAll("\\.", "/"));
    }
}
