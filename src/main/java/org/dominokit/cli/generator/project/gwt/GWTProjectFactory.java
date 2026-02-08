package org.dominokit.cli.generator.project.gwt;

import org.dominokit.cli.generator.exception.InvalidProjectTypeException;
import org.dominokit.cli.generator.project.ProjectCreator;

/**
 * Factory for GWT project generators.
 */
public class GWTProjectFactory {

    /**
     * Returns a project creator for a GWT project type.
     *
     * @param projectType project type
     * @return project creator
     */
    public static ProjectCreator get(String projectType) {
        switch (projectType.toLowerCase()){
            case "basic" : return new GwtBasicProject();
            case "brix" : return new GwtBrixProject();
            default:throw new InvalidProjectTypeException("Invalid project type : ["+projectType+"]");
        }
    }
}
