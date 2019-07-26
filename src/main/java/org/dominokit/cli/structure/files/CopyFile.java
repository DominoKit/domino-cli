package org.dominokit.cli.structure.files;

import org.dominokit.cli.model.IsContext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CopyFile implements ProjectFile {

    private final String name;
    private String resourceName;

    public CopyFile(String name, String resourceName) {
        this.name = name;
        this.resourceName = resourceName;
    }

    @Override
    public void write(Path path, IsContext context) throws IOException {

        String content = new ResourceFileContentProcessor(resourceName).processedContent();

        Path targetPath = Paths.get(path.toAbsolutePath().toString(), name);
        Files.write(targetPath, content.getBytes(), StandardOpenOption.CREATE);
    }
}
