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

public class PomUtil {

    public static String asString(Model model) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(model.getPomFile().getAbsolutePath()));
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        return data;
    }

    public static Model asModel(String path) throws IOException {
        Path pomPath = Paths.get(PathUtils.getUserDir(), path, "pom.xml");
        ModelReader modelReader = new DefaultModelReader();
        return modelReader.read(new File(pomPath.toAbsolutePath().toString()), null);
    }

}
