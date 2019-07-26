package org.dominokit.cli.generator;

import org.apache.commons.io.FileUtils;
import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.model.Module;
import org.dominokit.cli.structure.files.VelocityContentProcessor;
import org.dominokit.cli.structure.folders.Folder;
import org.dominokit.cli.structure.folders.Folder_MapperImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class MultiModuleGenerator {

    public void generate(Module module) throws IOException {
        String projectTemplateConfig = new VelocityContentProcessor("template/module/multi.json", module)
                .processedContent();
        Folder folder = Folder_MapperImpl.INSTANCE
                .read(projectTemplateConfig);
        folder.write(Paths.get(PathUtils.getUserDir()), module);
        addDependencies(module);
    }

    private void addDependencies(Module module) throws IOException {
        String frontEndPomString = PomUtil.asString(module.getFrontendPom());

        addFrontEndDependencies(module, frontEndPomString);
        addBackendDependencies(module);
    }

    private void addBackendDependencies(Module module) throws IOException {
        String backendPomString = PomUtil.asString(module.getBackendPom());

        backendPomString = backendPomString
                .replace("</dependencies>",
                        "\n\t\t<dependency>" +
                                "\n\t\t\t<groupId>" + module.getProject().getGroupId() + "</groupId>" +
                                "\n\t\t\t<artifactId>" + module.getArtifactId() + "-backend</artifactId>" +
                                "\n\t\t\t<version>${project.version}</version>" +
                                "\n\t\t</dependency>" +
                                "\n\t</dependencies>");

        FileUtils.write(module.getBackendPom().getPomFile(), backendPomString, StandardCharsets.UTF_8);
    }

    private void addFrontEndDependencies(Module module, String frontEndPomString) throws IOException {
        frontEndPomString = frontEndPomString
                .replace("</dependencies>",
                        "\n\t\t<dependency>" +
                                "\n\t\t\t<groupId>" + module.getProject().getGroupId() + "</groupId>" +
                                "\n\t\t\t<artifactId>" + module.getArtifactId() + "-frontend</artifactId>" +
                                "\n\t\t\t<version>${project.version}</version>" +
                                "\n\t\t</dependency>" +

                                "\n" +
                                "\n\t\t<dependency>" +
                                "\n\t\t\t<groupId>" + module.getProject().getGroupId() + "</groupId>" +
                                "\n\t\t\t<artifactId>" + module.getArtifactId() + "-frontend</artifactId>" +
                                "\n\t\t\t<version>${project.version}</version>" +
                                "\n\t\t\t<classifier>sources</classifier>" +
                                "\n\t\t</dependency>" +
                                "\n" +
                                "\n\t\t<dependency>" +
                                "\n\t\t\t<groupId>" + module.getProject().getGroupId() + "</groupId>" +
                                "\n\t\t\t<artifactId>" + module.getArtifactId() + "-frontend-ui</artifactId>" +
                                "\n\t\t\t<version>${project.version}</version>" +
                                "\n\t\t</dependency>" +
                                "\n" +
                                "\n\t\t<dependency>" +
                                "\n\t\t\t<groupId>" + module.getProject().getGroupId() + "</groupId>" +
                                "\n\t\t\t<artifactId>" + module.getArtifactId() + "-frontend-ui</artifactId>" +
                                "\n\t\t\t<version>${project.version}</version>" +
                                "\n\t\t\t<classifier>sources</classifier>" +
                                "\n\t\t</dependency>" +
                                "\n\t</dependencies>");

        FileUtils.write(module.getFrontendPom().getPomFile(), frontEndPomString, StandardCharsets.UTF_8);
    }
}
