package org.dominokit.cli.generator;

import org.apache.commons.io.IOUtils;
import org.dominokit.cli.VersionProfile;
import org.dominokit.cli.generator.exception.FailedToCreateResourceException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Supplier;

public class ResourceFile implements ProjectFile {

    private final String name;
    private final String template;
    private Supplier<Boolean> condition = () -> true;

    public ResourceFile(String name, String template) {
        this.name = name;
        this.template = template;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Supplier<Boolean> condition() {
        return condition;
    }

    public ResourceFile setCondition(Supplier<Boolean> condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public void write(String path, Map<String, Object> context) {
        if(condition().get()) {
            try {
                Path filePath = Paths.get(path, name).toAbsolutePath();
                File file = filePath.toFile();
                if (!file.exists()) {
                    byte[] content = IOUtils.resourceToByteArray("projects-templates/"+ VersionProfile.get().getTemplatesPath()+template, getClass().getClassLoader());
                    Files.write(filePath, content);
                }
            } catch (IOException  e) {
                throw new FailedToCreateResourceException("Failed to write file, path : " + path + ", name : " + name, e);
            }
        }
    }
}
