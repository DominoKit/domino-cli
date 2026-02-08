package org.dominokit.cli;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelReader;
import org.apache.maven.model.io.ModelReader;
import org.dominokit.cli.commands.PathUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility helpers for reading Maven POMs.
 */
public class PomUtil {

    /**
     * Reads the POM file contents for a model.
     *
     * @param model Maven model
     * @return POM XML as a string
     * @throws IOException if the file cannot be read
     */
    public static String asString(Model model) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(model.getPomFile().getAbsolutePath()));
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        return data;
    }

    /**
     * Reads a POM model from the given path under the working directory.
     *
     * @param path relative path to the module directory
     * @return parsed Maven model
     * @throws IOException if the file cannot be read
     */
    public static Model asModel(String path) throws IOException {
        Path pomPath = Paths.get(PathUtils.getUserDir(), path, "pom.xml");
        ModelReader modelReader = new DefaultModelReader();
        return modelReader.read(new File(pomPath.toAbsolutePath().toString()), null);
    }
}
