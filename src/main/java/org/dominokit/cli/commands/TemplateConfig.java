package org.dominokit.cli.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TemplateConfig {
    private String name;
    private Path templateRoot;
    private Path resourcesRoot = Paths.get("src/main/resources");
    private final List<String> resourcesExtensions;
    private Map<String, String> conditions = new HashMap<>();

    public TemplateConfig(String name, Path templateRoot) throws IOException {
        Properties properties = new Properties();
        properties.load(Test.class.getClassLoader().getResourceAsStream("cli-config.properties"));
        resourcesExtensions = Arrays.asList(properties.getProperty("resources.extensions").split(","));
        this.templateRoot = templateRoot;
        this.name = name;
    }

    public Path getTemplateRoot() {
        return templateRoot;
    }

    public void setTemplateRoot(Path templateRoot) {
        this.templateRoot = templateRoot;
    }

    public Path getResourcesRoot() {
        return resourcesRoot;
    }

    public void setResourcesRoot(Path resourcesRoot) {
        this.resourcesRoot = resourcesRoot;
    }

    public List<String> getResourcesExtensions() {
        return resourcesExtensions;
    }

    public Map<String, String> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, String> conditions) {
        this.conditions = conditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition(File file) {
        String path = templateRelativePath(file);
        if (conditions.containsKey(path)) {
            return conditions.get(path);
        }
        return "true";
    }

    public String templateRelativePath(File subFile) {
        return subFile.getAbsolutePath().replace(getResourcesRoot().toAbsolutePath() + "/".toString(), "");
    }
}