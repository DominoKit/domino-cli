package org.dominokit.cli.commands;

import org.dominokit.cli.model.Project;
import org.dominokit.cli.structure.files.VelocityContentProcessor;
import org.dominokit.cli.structure.folders.Folder;
import org.dominokit.cli.structure.folders.Folder_MapperImpl;

import java.io.IOException;
import java.nio.file.Paths;

import static picocli.CommandLine.*;

@Command(
        name = "app",
        description = "Use with generate command to generate a domino-mvp template project",
        subcommands = HelpCommand.class
)
public class GenerateAppCommand implements Runnable {

    private static final String VERSION = "1.0-SNAPSHOT";
    @Option(
            names = {"-n", "--name"},
            description = "The project name, also will be use as the artifact ID",
            required = true
    )
    private String name;

    @Option(
            names = {"-g", "--groupId"},
            description = "The project group ID, this will be used also for root package name",
            required = true
    )
    private String groupId;

    @Option(
            names = {"-d", "--dir"},
            description = "absolute path to the directory where the project should be generated."
    )
    private String workingDire;

    @Override
    public void run() {

        PathUtils.setWorkingDir(workingDire);

        Project project = new Project();
        project.setName(name);
        project.setArtifactId(name);
        project.setGroupId(groupId);
        project.setVersion(VERSION);
        project.setRootPackage(groupId);

        try {

            String projectTemplateConfig = new VelocityContentProcessor("template/project/domino-mvp.json", project)
                    .processedContent();
            Folder folder = Folder_MapperImpl.INSTANCE
                    .read(projectTemplateConfig);
            folder.write(Paths.get(PathUtils.getUserDir()), project);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
