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
            generateDescriptor(Paths.get("src/main/resources/app/gwt/mvp"), "domino-mvp.json", "project/gwt");
            generateDescriptor(Paths.get("src/main/resources/app/gwt/basic"), "domino-basic.json", "project/gwt");
            generateDescriptor(Paths.get("src/main/resources/app/j2cl/mvp"), "domino-mvp.json", "project/j2cl");
            generateDescriptor(Paths.get("src/main/resources/app/j2cl/basic"), "domino-basic.json", "project/j2cl");

            generateDescriptor(Paths.get("src/main/resources/module/gwt/single"), "single.json", "module/gwt");

            TemplateConfig multiGwtModuleConfig = new TemplateConfig("${artifactId}", Paths.get("src/main/resources/module/gwt/multi"));
            multiGwtModuleConfig.getConditions().put("module/gwt/multi/__artifactId__-frontend/src/test.java.__rootPackage__.__subpackage__.client", "${generateTests}");
            generateDescriptor(multiGwtModuleConfig, "multi.json", "module/gwt");

            generateDescriptor(Paths.get("src/main/resources/module/j2cl/single"), "single.json", "module/j2cl");

            TemplateConfig multiJ2clModuleConfig = new TemplateConfig("${artifactId}", Paths.get("src/main/resources/module/j2cl/multi"));
            multiJ2clModuleConfig.getConditions().put("module/j2cl/multi/__artifactId__-frontend/src/test.java.__rootPackage__.__subpackage__.client", "${generateTests}");
            generateDescriptor(multiJ2clModuleConfig, "multi.json", "module/j2cl");

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
