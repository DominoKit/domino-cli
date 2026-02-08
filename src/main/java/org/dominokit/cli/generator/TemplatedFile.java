package org.dominokit.cli.generator;

import freemarker.template.TemplateException;
import org.dominokit.cli.generator.exception.FailedToCreateResourceException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Renders a FreeMarker template to disk.
 */
public class TemplatedFile implements ProjectFile {

    private final String name;
    private final String template;
    private Supplier<Boolean> condition = () -> true;

    /**
     * Creates a templated file definition.
     *
     * @param name output file name
     * @param template template path under templates root
     */
    public TemplatedFile(String name, String template) {
        this.name = name;
        this.template = template;
    }

    /**
     * Returns the output file name.
     *
     * @return output file name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the conditional predicate for file creation.
     *
     * @return condition supplier
     */
    @Override
    public Supplier<Boolean> condition() {
        return condition;
    }

    /**
     * Sets the condition controlling whether the file is created.
     *
     * @param condition condition supplier
     * @return this instance
     */
    public TemplatedFile setCondition(Supplier<Boolean> condition) {
        this.condition = condition;
        return this;
    }

    /**
     * Writes the rendered template to disk if it does not already exist.
     *
     * @param path root path to write into
     * @param context template context
     */
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
