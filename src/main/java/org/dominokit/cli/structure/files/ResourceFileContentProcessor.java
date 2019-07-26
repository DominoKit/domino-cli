package org.dominokit.cli.structure.files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceFileContentProcessor implements FileContentProcessor {

    private static final Logger LOGGER = Logger.getLogger(ResourceFileContentProcessor.class.getCanonicalName());

    private String resourceName;

    public ResourceFileContentProcessor(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String processedContent() {

        try {
            LOGGER.log(Level.INFO, "Loading resource "+resourceName);
            InputStream resourceAsStream = getClass().getClassLoader()
                    .getResourceAsStream(resourceName);
            Stream<String> lines = IOUtils.readLines(resourceAsStream, StandardCharsets.UTF_8).stream();
            String data = lines.collect(Collectors.joining("\n"));
            lines.close();

            return data;
        } catch (Exception e) {
            throw new ProcessContentException(resourceName, e);
        }
    }
}
