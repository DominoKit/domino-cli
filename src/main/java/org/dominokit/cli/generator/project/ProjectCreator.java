package org.dominokit.cli.generator.project;

/**
 * Contract for creating project scaffolds.
 */
public interface ProjectCreator {
    /**
     * Creates the project structure on disk.
     *
     * @param project project model
     */
    void create(Project project);
}
