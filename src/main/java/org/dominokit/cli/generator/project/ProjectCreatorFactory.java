package org.dominokit.cli.generator.project;

import org.dominokit.cli.generator.project.gwt.GWTProjectFactory;

/**
 * Factory for selecting the correct project generator by type.
 */
public class ProjectCreatorFactory {

    /**
     * Returns a project creator for the given type.
     *
     * @param projectType project type
     * @return project creator
     */
    public static ProjectCreator get(String projectType){
        return GWTProjectFactory.get(projectType);
    }
}
