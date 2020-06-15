package org.dominokit.cli.commands;

import org.apache.commons.io.FileUtils;
import org.apache.maven.model.Model;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.generator.MultiModuleGenerator;
import org.dominokit.cli.generator.SingleModuleGenerator;
import org.dominokit.cli.model.Module;
import org.dominokit.cli.model.Project;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static picocli.CommandLine.*;

@Command(
        name = "module",
        description = "Use with generate command to generate a domino-mvp module template",
        subcommands = HelpCommand.class
)
public class GenerateModuleCommand implements Runnable {

    private static final String VERSION = "1.0-SNAPSHOT";
    @Option(
            names = {"-n", "--name"},
            description = "The module name, also will be use as the artifact ID",
            required = true
    )
    private String name;

    @Option(
            names = {"-s", "--single"},
            fallbackValue = "true",
            defaultValue = "false",
            description = "if true will split the module into more submodules, [name]-backend, [name]-frontend, [name]-frontend-ui, [name]-shared"
    )
    private boolean single = false;

    @Option(
            names = {"-t", "--tests"},
            fallbackValue = "true",
            defaultValue = "false",
            description = "if true will generate tests for a multi submodules module."
    )
    private boolean generateTests = false;

    @Option(
            names = {"-j", "--j2cl"},
            fallbackValue = "true",
            defaultValue = "false",
            description = "if true will generate a module that target j2cl compiler."
    )
    private boolean j2cl = false;

    @Option(
            names = {"-sp", "--subpackage"},
            description = "the module sub package, this will be appended to the application rootPackage"
    )
    private String subPackage;

    @Option(
            names = {"-d", "--dir"},
            description = "absolute path to the module where the project should be generated."
    )
    private String workingDire;

    @Override
    public void run() {


        PathUtils.setWorkingDir(workingDire);

        try {

            Model projectPom = PomUtil.asModel("");

            String artifactId = projectPom.getArtifactId();

            Model frontendPom = PomUtil.asModel(artifactId + "-frontend");
            Model backendPom = PomUtil.asModel(artifactId + "-backend");

            Project project = new Project();

            project.setName(artifactId);
            if (nonNull(projectPom.getParent())) {
                project.setGroupId(projectPom.getParent().getGroupId());
                project.setRootPackage(projectPom.getParent().getGroupId());
                project.setVersion(projectPom.getParent().getVersion());
            } else {
                project.setGroupId(projectPom.getGroupId());
                project.setRootPackage(projectPom.getGroupId());
                project.setVersion(projectPom.getVersion());
            }
            project.setArtifactId(projectPom.getArtifactId());

            Module module = new Module(project);
            module.setProjectPom(projectPom);
            module.setBackendPom(backendPom);
            module.setFrontendPom(frontendPom);
            module.setArtifactId(name);
            module.setGenerateTests(generateTests);
            module.setJ2cl(j2cl);

            if (isNull(subPackage) || subPackage.trim().isEmpty()) {
                subPackage = name.toLowerCase()
                        .replace("_", ".")
                        .replace("-", ".");
            }
            module.setSubPackage(subPackage);

            if (single) {
                new SingleModuleGenerator().generate(module);
            } else {
                new MultiModuleGenerator().generate(module);
            }

            addModule(module);
            System.out.println(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Module generated with the following arguments" +
                "\nname=" + name +
                "\nsingle=" + single +
                "\ngenerateTests=" + generateTests +
                "\nj2cl=" + j2cl +
                "\nsubPackage=" + subPackage +
                "\nworkingDire=" + workingDire;
    }

    private void addModule(Module module) throws IOException {
        String projectPomString = PomUtil.asString(module.getProjectPom());

        if (projectPomString.contains("<modules>")) {
            projectPomString = projectPomString.replace("</modules>", "\t<module>" + module.getArtifactId() + "</module>\n\t</modules>");
        } else {
            projectPomString = projectPomString.replace("</project>", "\n\t<modules>\n\t\t<module>" + module.getArtifactId() + "</module>\n\t</modules>\n</project>");
        }
        FileUtils.write(module.getProjectPom().getPomFile(), projectPomString, StandardCharsets.UTF_8);

    }

}
