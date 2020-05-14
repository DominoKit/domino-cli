package org.dominokit.cli.structure.folders;

import org.dominokit.cli.model.IsContext;
import org.dominokit.cli.structure.files.ProjectFile;
import org.dominokit.cli.structure.files.TemplateFile;
import org.dominokit.jacksonapt.annotation.JSONMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@JSONMapper
public class Folder {

    public static final NullFolder NONE = new NullFolder();

    private String name;
    private String condition = "true";
    private List<Folder> folders = new ArrayList<>();
    private List<TemplateFile> files = new ArrayList<>();

    public Folder() {
    }

    public Folder(String name) {
        this.name = name;
    }

    public static Folder create(String name) {
        return new Folder(name);
    }

    public static Folder fromPackage(String pckg) {
        String[] split = pckg.split("\\.");
        if (split.length < 1) {
            return Folder.NONE;
        }

        Folder root = Folder.create(split[0]);
        Folder parent = root;
        for (int i = 1; i < split.length; i++) {
            Folder folder = Folder.create(split[i]);
            parent.append(folder);
            parent = folder;
        }

        return root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public static NullFolder getNONE() {
        return NONE;
    }

    public List<TemplateFile> getFiles() {
        return files;
    }

    public void setFiles(List<TemplateFile> files) {
        this.files = files;
    }

    public Folder append(Folder folder) {
        this.folders.add(folder);
        return this;
    }

    public Folder append(ProjectFile projectFile) {
        this.files.add((TemplateFile) projectFile);
        return this;
    }

    public Folder edit(Consumer<Folder> consumer) {
        consumer.accept(this);
        return this;
    }

    public Folder lastChild() {
        if (!folders.isEmpty()) {
            return folders.get(folders.size() - 1);
        }
        return this;
    }

    public Folder deepLastChild() {
        if (!folders.isEmpty()) {
            return folders.get(folders.size() - 1).deepLastChild();
        }
        return this;
    }

    public Folder childAt(int index) {
        if (index >= 0 && index < folders.size()) {
            return folders.get(index);
        }
        return Folder.NONE;
    }

    public Folder childByName(String name) {
        return folders.stream()
                .filter(folder -> folder.name.equals(name))
                .findFirst()
                .orElse(Folder.NONE);
    }

    public void write(Path path, IsContext context) throws IOException {
        if(Boolean.valueOf(condition)) {
            if (name.contains(".") && !name.startsWith(".")) {
                Folder folder = Folder.fromPackage(name);
                folder.deepLastChild().setFiles(files);
                folder.deepLastChild().setFolders(folders);
                this.files = new ArrayList<>();
                this.folders = new ArrayList<>();
                folder.write(path, context);
            } else {
                Path targetPath = Paths.get(path.toAbsolutePath().toString(), name);
                if(!Files.exists(targetPath)) {
                    Files.createDirectory(targetPath);
                }

                for (ProjectFile projectFile : files) {
                    projectFile.write(targetPath, context);
                }

                for (Folder folder : folders) {
                    folder.write(targetPath, context);
                }
            }
        }
    }

    public static class NullFolder extends Folder {

        public NullFolder() {
            super(null);
        }

        @Override
        public Folder append(Folder folder) {
            return this;
        }

        @Override
        public Folder append(ProjectFile projectFile) {
            return this;
        }

        @Override
        public Folder lastChild() {
            return this;
        }

        @Override
        public void write(Path path, IsContext context) throws IOException {

        }
    }
}
