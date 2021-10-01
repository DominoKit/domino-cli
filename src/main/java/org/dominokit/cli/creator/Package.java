package org.dominokit.cli.creator;

public class Package extends Folder {

    public Package(String name) {
        super(name.replaceAll("\\.", "/"));
    }
}
