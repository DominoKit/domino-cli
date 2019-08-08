package org.dominokit.cli.commands;

import org.apache.commons.io.FileUtils;
import org.dominokit.cli.structure.folders.Folder;
import org.dominokit.cli.structure.folders.Folder_MapperImpl;
import org.dominokit.jacksonapt.DefaultJsonSerializationContext;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DescriptorsGenerator {

    public static void generate() {
        try {
            generateDescriptor(Paths.get("src/main/resources/app/mvp"), "domino-mvp.json", "project");
            generateDescriptor(Paths.get("src/main/resources/app/ui"), "domino-ui.json", "project");
            generateDescriptor(Paths.get("src/main/resources/module/single"), "single.json", "module");

            TemplateConfig templateConfig = new TemplateConfig("${artifactId}", Paths.get("src/main/resources/module/multi"));
            templateConfig.getConditions().put("module/multi/__artifactId__-frontend/src/test.java.__rootPackage__.__subpackage__.client", "${generateTests}");
            generateDescriptor(templateConfig, "multi.json", "module");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateDescriptor(Path templateRoot, String targetJson, String targetFolder) throws IOException {
        TemplateConfig templateConfig = new TemplateConfig("${artifactId}", templateRoot);
        Folder projectFolder = TemplateDescriptorGenerator.generate(templateConfig);
        FileUtils.write(new File(Paths.get("src/main/resources/template/" + targetFolder, targetJson).toAbsolutePath().toString()), Folder_MapperImpl.INSTANCE.write(projectFolder, DefaultJsonSerializationContext.builder()
                .indent(true)
                .build()), StandardCharsets.UTF_8);
    }

    private static void generateDescriptor(TemplateConfig templateConfig, String targetJson, String targetFolder) throws IOException {
        Folder projectFolder = TemplateDescriptorGenerator.generate(templateConfig);
        FileUtils.write(new File(Paths.get("src/main/resources/template/" + targetFolder, targetJson).toAbsolutePath().toString()), Folder_MapperImpl.INSTANCE.write(projectFolder, DefaultJsonSerializationContext.builder()
                .indent(true)
                .build()), StandardCharsets.UTF_8);
    }
}
