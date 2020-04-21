package org.dominokit.cli.commands;

import org.dominokit.cli.model.Project;
import org.dominokit.cli.structure.files.VelocityContentProcessor;
import org.dominokit.cli.structure.folders.Folder;
import org.dominokit.cli.structure.folders.Folder_MapperImpl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static picocli.CommandLine.*;

@Command(
        name = "app",
        description = "Use with generate command to generate a domino-mvp template project",
        subcommands = HelpCommand.class
)
public class GenerateAppCommand implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(GenerateAppCommand.class.getName());

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

    @Option(
            names = {"-t", "--type"},
            description = "The type of the project, available types are [mvp,basic], [mvp] will generate a domino-mvp application, [basic] will generate simple gwt with domino-ui application.",
            defaultValue = "mvp"
    )
    private String type;

    @Option(
            names = {"-j", "--j2cl"},
            defaultValue = "false",
            description = "if true will generate a module that target j2cl compiler."
    )
    private boolean j2cl = false;

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

            String projectTemplateConfig = new VelocityContentProcessor(getTemplateByType(j2cl, type), project)
                    .processedContent();
            Folder folder = Folder_MapperImpl.INSTANCE
                    .read(projectTemplateConfig);
            folder.write(Paths.get(PathUtils.getUserDir()), project);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTemplateByType(boolean j2cl, String type) {
        String compiler = j2cl?"j2cl":"gwt";

        if(type.equalsIgnoreCase("mvp")){
            return "template/project/"+compiler+"/domino-mvp.json";
        }else if(type.equalsIgnoreCase("basic")){
            return "template/project/"+compiler+"/domino-basic.json";
        }
        LOGGER.log(Level.SEVERE, "Unrecognized application type : "+type);
        throw new IllegalArgumentException("Unrecognized application type : "+type);
    }
}
