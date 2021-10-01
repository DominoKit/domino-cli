package org.dominokit.cli.creator.module;

import java.io.IOException;

public interface ModuleCreator {
    void create(Module module) throws IOException;
}
