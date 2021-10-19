package org.dominokit.cli.generator.module;

import java.io.IOException;

public interface ModuleCreator {
    void create(Module module) throws IOException;
}
