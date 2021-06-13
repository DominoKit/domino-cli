package org.dominokit.cli.commands;

import org.dominokit.cli.structure.files.TemplateFile;
import org.dominokit.cli.structure.files.TemplateType;
import org.dominokit.cli.structure.folders.Folder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TemplateDescriptorGenerator {

    public static Folder generate(TemplateConfig config) {
        return new TemplateDescriptorGenerator().readFolder(config);
    }

    private Folder readFolder(TemplateConfig templateConfig) {
        Folder rootFolder = Folder.create(templateConfig.getName());
        readFolder(templateConfig, new File(templateConfig.getTemplateRoot().toAbsolutePath().toString()), rootFolder);
        return rootFolder;
    }

    private void readFolder(TemplateConfig templateConfig, File file, Folder currentFolder) {
        if (file.exists() && file.isDirectory()) {
            for (File subFile : Arrays.asList(Objects.requireNonNull(file.listFiles()))) {
                if (subFile.isDirectory()) {
                    String finalName = subFile.getName();
                    if (subFile.getName().contains("__rootPackage__")) {
                        finalName = finalName.replace("__rootPackage__", "${rootPackage}");
                    }
                    if (subFile.getName().contains("__subpackage__")) {
                        finalName = finalName.replace("__subpackage__", "${subpackage}");
                    }
                    if (subFile.getName().contains("__name__")) {
                        finalName = finalName.replace("__name__", "${name}");
                    }
                    if (subFile.getName().contains("__artifactId__")) {
                        finalName = finalName.replace("__artifactId__", "${artifactId}");
                    }
                    if (subFile.getName().contains("__moduleShortName__")) {
                        finalName = finalName.replace("__moduleShortName__", "${moduleShortName}");
                    }
                    Folder folder = Folder.create(finalName);
                    folder.setCondition(templateConfig.getCondition(subFile));
                    currentFolder.append(folder);
                    readFolder(templateConfig, subFile, folder);
                } else {
                    TemplateFile templateFile = new TemplateFile();
                    String finalName = subFile.getName();
                    if (finalName.contains("__name__")) {
                        finalName = finalName.replace("__name__", "${name}");
                    }
                    if (finalName.contains("__moduleName__")) {
                        finalName = finalName.replace("__moduleName__", "${moduleName}");
                    }
                    if (finalName.contains("__artifactId__")) {
                        finalName = finalName.replace("__artifactId__", "${artifactId}");
                    }
                    if (subFile.getName().contains("__moduleShortName__")) {
                        finalName = finalName.replace("__moduleShortName__", "${moduleShortName}");
                    }
                    templateFile.setName(finalName);
                    boolean resource = isResourceType(templateConfig.getResourcesExtensions(), templateConfig.getResourcesNames(), subFile.getName());
                    templateFile.setType(resource ? TemplateType.RESOURCE : TemplateType.VELOCITY);
                    templateFile.setTemplate(templateConfig.templateRelativePath(subFile));
                    currentFolder.append(templateFile);
                }
            }
        }
    }

    private boolean isResourceType(List<String> resourcesExtensions, List<String> resourcesNames, String name) {
        if (resourcesNames.contains(name)) {
            return true;
        }
        for (String extension : resourcesExtensions) {
            if (name.endsWith("." + extension)) {
                return true;
            }
        }
        return false;
    }
}
