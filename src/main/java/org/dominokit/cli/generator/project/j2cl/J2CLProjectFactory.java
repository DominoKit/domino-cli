package org.dominokit.cli.generator.project.j2cl;

import org.dominokit.cli.generator.exception.InvalidProjectTypeException;
import org.dominokit.cli.generator.project.ProjectCreator;

public class J2CLProjectFactory {

    public static ProjectCreator get(String projectType) {
        switch (projectType.toLowerCase()){
            case "basic" : return new J2clBasicProject();
            case "mvp" : return new J2clMVPProject();
            default:throw new InvalidProjectTypeException("Invalid project type : ["+projectType+"]");
        }
    }
}
