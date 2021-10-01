package org.dominokit.cli.creator.project;

import org.dominokit.cli.creator.exception.InvalidProjectTypeException;

public class J2CLProjectFactory {

    public static ProjectCreator get(String projectType) {
        switch (projectType.toLowerCase()){
            case "basic" : return new J2clBasicProject();
            case "mvp" : return new J2clMVPProject();
            default:throw new InvalidProjectTypeException("Invalid project type : ["+projectType+"]");
        }
    }
}
