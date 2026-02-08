package org.dominokit.cli.commands;

import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Model;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.VersionProfile;
import org.dominokit.cli.generator.module.Module;
import org.dominokit.cli.generator.module.ModuleCreatorFactory;
import org.dominokit.cli.generator.project.Project;
import org.dominokit.cli.generator.project.ProjectCreator;
import org.dominokit.cli.generator.project.ProjectCreatorFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Shared app generation logic for app, brix-app, and basic-app commands.
 */
public final class AppGenerator {

    public static final String DEFAULT_VERSION = "1.0-SNAPSHOT";

    private AppGenerator() {
    }

    public static void generate(String name,
                                String groupId,
                                String workingDir,
                                String type,
                                boolean dev,
                                boolean generateApi,
                                String defaultVersion,
                                Logger logger) {
        VersionProfile.setVersion(dev ? "dev" : "release");

        Model parentPom = null;
        PathUtils.setWorkingDir(workingDir);
        try {
            parentPom = PomUtil.asModel("");
        } catch (Exception e) {
            logger.info("No parent pom was found, creating a root project.");
        }

        Project project = new Project(name);
        project.setArtifactId(name);

        if (nonNull(parentPom)) {
            project.setVersion(parentPom.getVersion());
            project.setParentArtifactId(parentPom.getArtifactId());
            project.setHasParent(true);
            project.setGroupId(parentPom.getGroupId());
            if (isNull(groupId)) {
                groupId = parentPom.getGroupId();
            }
        } else {
            project.setVersion(defaultVersion);
            project.setGroupId(groupId);
        }

        project.setRootPackage(groupId);
        project.setModuleShortName(name
                .replace("-", "")
                .replace(".", "")
                .replace(" ", "")
        );

        project.setGenerateApi(generateApi);
        project.setCompiler("gwt");

        try {
            ProjectCreator projectCreator = ProjectCreatorFactory.get(type);
            projectCreator.create(project);

            if (project.isHasParent()) {
                addProjectToParent(project, parentPom);
            }

            System.out.println("The following project have been created");
            System.out.println(project);

            maybeGenerateShellModule(project, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void maybeGenerateShellModule(Project project, String type) {
        if (!"brix".equalsIgnoreCase(type)) {
            return;
        }
        if (!confirmShellModule()) {
            return;
        }
        String projectRoot = Paths.get(PathUtils.getUserDir(), project.getName()).toString();
        PathUtils.setWorkingDir(projectRoot);
        try {
            Module module = new Module("shell");
            module.setArtifactId("shell");
            module.setCompiler("gwt");
            module.setPrefix("Shell");
            module.setSubPackage("shell");
            ModuleCreatorFactory.get().create(module.init());
            System.out.println("Default shell module has been created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean confirmShellModule() {
        System.out.print("Generate a default Shell module? [y/N]: ");
        if (System.console() != null) {
            String line = System.console().readLine();
            return line != null && ("y".equalsIgnoreCase(line.trim()) || "yes".equalsIgnoreCase(line.trim()));
        }
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) {
            return false;
        }
        String line = scanner.nextLine();
        return line != null && ("y".equalsIgnoreCase(line.trim()) || "yes".equalsIgnoreCase(line.trim()));
    }

    /**
     * Adds the newly generated module to the parent POM <modules> list.
     *
     * @param project the generated project
     * @param parentPomModel parent Maven model to update
     * @throws IOException if writing the POM fails
     */
    private static void addProjectToParent(Project project, Model parentPomModel) throws IOException {
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
