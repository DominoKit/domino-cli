package org.dominokit.cli.creator.project;

import org.dominokit.cli.creator.exception.InvalidCompilerTypeException;

public class ProjectCreatorFactory {

    public static final ProjectCreator get(String compiler, String projectType){

        switch (compiler.toLowerCase()){
            case "gwt": return GWTProjectFactory.get(projectType);
            case "j2cl": return J2CLProjectFactory.get(projectType);
            default:throw new InvalidCompilerTypeException("Invalid compiler type : ["+compiler+"]");
        }
    }
}
