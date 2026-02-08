package org.dominokit.cli.generator.project;

/**
 * Contract for creating sample project content.
 */
public interface SampleCreator {
    /**
     * Creates sample content inside the given project.
     *
     * @param project project model
     */
    void create(Project project);
}
