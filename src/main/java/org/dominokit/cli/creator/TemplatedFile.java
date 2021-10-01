package org.dominokit.cli.creator;

import freemarker.template.TemplateException;
import org.dominokit.cli.creator.exception.FailedToCreateResourceException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Supplier;

public class TemplatedFile implements ProjectFile {

    private final String name;
    private final String template;
    private Supplier<Boolean> condition = () -> true;

    public TemplatedFile(String name, String template) {
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

    public TemplatedFile setCondition(Supplier<Boolean> condition) {
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
                    TemplateProvider.render(template, context, new FileWriter(file));
                }
            } catch (IOException | TemplateException e) {
                throw new FailedToCreateResourceException("Failed to write file, path : " + path + ", name : " + name, e);
            }
        }
    }
}
