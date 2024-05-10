package org.dominokit.cli.generator.project.gwt;

import org.dominokit.cli.generator.exception.InvalidProjectTypeException;
import org.dominokit.cli.generator.project.ProjectCreator;

public class GWTProjectFactory {

    public static ProjectCreator get(String projectType) {
        switch (projectType.toLowerCase()){
            case "basic" : return new GwtBasicProject();
            case "mvp" : return new GwtMVPProject();
            case "brix" : return new GwtBrixProject();
            default:throw new InvalidProjectTypeException("Invalid project type : ["+projectType+"]");
        }
    }
}
