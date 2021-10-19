package org.dominokit.cli.generator;


import org.dominokit.cli.generator.exception.FailedToCreateResourceException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Folder {

    private List<Folder> folders= new ArrayList<>();
    private List<ProjectFile> files=  new ArrayList<>();

    private String relativePath;
    private String name;
    private Supplier<Boolean> condition = () -> true;

    public Folder(String relativePath, String name) {
        this.relativePath = relativePath;
        this.name = name;
    }
    public Folder(String name) {
        this.relativePath = "";
        this.name = name;
    }

    public Folder add(Folder folder){
        folders.add(folder);
        return this;
    }

    public Folder add(ProjectFile file){
        files.add(file);
        return this;
    }

    public Folder setCondition(Supplier<Boolean> condition) {
        this.condition = condition;
        return this;
    }

    public void write(String rootPath, Map<String, Object> context) {
        if(condition.get()) {
            try {
                Path dir = Paths.get(rootPath, relativePath, name);
                Files.createDirectories(dir);
                folders.forEach(folder -> folder.write(dir.toString(), context));
                files.forEach(projectFile -> projectFile.write(dir.toString(), context));
            } catch (IOException e) {
                throw new FailedToCreateResourceException("Failed to create folder [" + relativePath + "," + name + "]", e);
            }
        }
    }

}
