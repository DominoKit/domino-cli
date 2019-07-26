package org.dominokit.cli.structure.files;

import org.dominokit.cli.model.IsContext;

import java.io.IOException;
import java.nio.file.Path;

public interface ProjectFile {
    void write(Path path, IsContext context) throws IOException;
}
