package org.dominokit.cli.generator.module;

import java.io.IOException;

/**
 * Contract for creating module scaffolds.
 */
public interface ModuleCreator {
    /**
     * Creates the module structure on disk.
     *
     * @param module module model
     * @throws IOException when file creation fails
     */
    void create(Module module) throws IOException;
}
