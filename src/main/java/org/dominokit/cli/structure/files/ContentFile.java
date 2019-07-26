package org.dominokit.cli.structure.files;

import org.dominokit.cli.model.IsContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContentFile implements ProjectFile {

    private final String name;
    private final String content;

    public ContentFile(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public void write(Path path, IsContext context) throws IOException {
        Path targetPath = Paths.get(path.toAbsolutePath().toString(), name);
        Files.write(targetPath, content.getBytes());
    }
}
