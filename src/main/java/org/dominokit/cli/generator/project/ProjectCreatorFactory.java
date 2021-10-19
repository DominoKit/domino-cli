package org.dominokit.cli.generator.project;

import org.dominokit.cli.generator.exception.InvalidCompilerTypeException;
import org.dominokit.cli.generator.project.gwt.GWTProjectFactory;
import org.dominokit.cli.generator.project.j2cl.J2CLProjectFactory;

public class ProjectCreatorFactory {

    public static final ProjectCreator get(String compiler, String projectType){

        switch (compiler.toLowerCase()){
            case "gwt": return GWTProjectFactory.get(projectType);
            case "j2cl": return J2CLProjectFactory.get(projectType);
            default:throw new InvalidCompilerTypeException("Invalid compiler type : ["+compiler+"]");
        }
    }
}
