package org.dominokit.cli.commands;

import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Model;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.creator.Project;
import org.dominokit.cli.creator.project.ProjectCreator;
import org.dominokit.cli.creator.project.ProjectCreatorFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.HelpCommand;
import static picocli.CommandLine.Option;

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
            description = "The project group ID, this will be used also for root package name"
    )
    private String groupId;

    @Option(
            names = {"-d", "--dir"},
            description = "absolute path to the directory where the project should be generated."
    )
    private String workingDire;

    @Option(
            names = {"-t", "--type"},
            description = "The type of the project :" +
                    "\n\t\t -[basic] : will generate a simple project with (client, shared, server)" +
                    "\n\t\t -[mvp] : will generate a domino-mvp project",
            defaultValue = "mvp"
    )
    private String type;

    @Option(
            names = {"-api", "--generate-api"},
            fallbackValue = "true",
            defaultValue = "true",
            description = "If true will generate an api module for REST endpoints implementation, current implementation is Quarkus with jax-rs."
    )
    private boolean generateApi;

    @Option(
            names = {"-c", "--compiler"},
            fallbackValue = "gwt",
            defaultValue = "gwt",
            description = "The Java to JavaScript compiler to be used possible values [gwt, j2cl] default is [gwt]"
    )
    private String compiler;

    @Override
    public void run() {

        Model parentPom = null;
        PathUtils.setWorkingDir(workingDire);
        try {
            parentPom = PomUtil.asModel("");
        } catch (Exception e) {
            LOGGER.info("No parent pom was found, creating a root project.");
        }

        Project project = new Project(name);

        project.setArtifactId(name);

        if (nonNull(parentPom)) {
            project.setVersion(parentPom.getVersion());
            project.setParentArtifactId(parentPom.getArtifactId());
            project.setHasParent(true);
            project.setGroupId(parentPom.getGroupId());
            if(isNull(groupId)) {
                groupId = parentPom.getGroupId();
            }
        } else {
            project.setVersion(VERSION);
            project.setGroupId(groupId);
        }

        project.setRootPackage(groupId);
        project.setModuleShortName(name
                .replace("-", "")
                .replace(".", "")
                .replace(" ", "")
        );

        project.setGenerateApi(generateApi);

        try {

            ProjectCreator projectCreator = ProjectCreatorFactory.get(compiler, type);
            projectCreator.create(project);

            if(project.isHasParent()){
                addProjectToParent(project, parentPom);
            }

            System.out.println("The following project have been created");
            System.out.println(project);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProjectToParent(Project project, Model parentPomModel) throws IOException {
        String parentPom;
        try {
            parentPom = PomUtil.asString(parentPomModel);
            if (parentPom.contains("<modules>")) {
                parentPom = parentPom.replace("</modules>", "\t<module>" + project.getArtifactId() + "</module>\n\t</modules>");
            } else {
                parentPom = parentPom.replace("</project>", "\n\t<modules>\n\t\t<module>" + project.getArtifactId() + "</module>\n\t</modules>\n</project>");
            }
            FileUtils.write(parentPomModel.getPomFile(), parentPom, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
