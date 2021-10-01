package org.dominokit.cli.creator.project;

import org.dominokit.cli.creator.exception.InvalidProjectTypeException;

public class GWTProjectFactory {

    public static ProjectCreator get(String projectType) {
        switch (projectType.toLowerCase()){
            case "basic" : return new GwtBasicProject();
            case "mvp" : return new GwtMVPProject();
            default:throw new InvalidProjectTypeException("Invalid project type : ["+projectType+"]");
        }
    }
}
