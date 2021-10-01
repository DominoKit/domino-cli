package org.dominokit.cli.creator.module;

import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.creator.exception.FailedToCreateResourceException;
import org.dominokit.cli.creator.Folder;
import org.dominokit.cli.creator.Package;
import org.dominokit.cli.creator.TemplateProvider;
import org.dominokit.cli.creator.TemplatedFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class GwtSingleModule implements ModuleCreator {

    public void create(Module module) throws IOException {
        module
                .add(new Folder("src/main")
                        .add(new Folder("java")
                                .add(new Package(module.getProject().getRootPackage())
                                        .add(new Package(module.getSubPackage())
                                                .add(new Folder("client")
                                                        .add(new Folder("presenters")
                                                                .add(new TemplatedFile(module.getPrefix() + "Proxy.java", "/module/gwt/single/source/Proxy.java"))
                                                        )
                                                        .add(new Folder("views")
                                                                .add(new Folder("ui")
                                                                        .add(new TemplatedFile(module.getPrefix() + "ViewImpl.java", "/module/gwt/single/source/ViewImpl.java"))
                                                                )
                                                                .add(new TemplatedFile(module.getPrefix() + "View.java", "/module/gwt/single/source/View.java"))
                                                        )
                                                        .add(new TemplatedFile(module.getModuleName() + "ClientModule.java", "/module/gwt/single/source/ClientModule.java"))
                                                )
                                                .add(new Folder("shared")
                                                        .add(new Folder("events")
                                                                .add(new TemplatedFile(module.getPrefix() + "Event.java", "/module/gwt/single/source/Event.java"))
                                                        )
                                                        .add(new Folder("services")
                                                                .add(new TemplatedFile(module.getPrefix() + "Service.java", "/module/gwt/single/source/Service.java"))
                                                        )
                                                )
                                        )
                                )
                        )
                        .add(new TemplatedFile("module.gwt.xml", "/module/gwt/single/source/module.gwt.xml"))
                )
                .add(new TemplatedFile("pom.xml", "/module/gwt/single/pom.xml"))
                .write(Paths.get(PathUtils.getUserDir()).toString(), module.context());

        addDependency(module);
        addModule(module);
    }

    private void addDependency(Module module) {
        try {
            String frontEndPomString = PomUtil.asString(module.getFrontendPom());

            if (!frontEndPomString.contains("<artifactId>" + module.getArtifactId() + "</artifactId>")) {
                String result = TemplateProvider.render("/module/gwt/single/frontend-dependency.ftl", module.context());
                frontEndPomString = frontEndPomString.replace("</dependencies>", result+"\t</dependencies>");
                FileUtils.write(module.getFrontendPom().getPomFile(), frontEndPomString, StandardCharsets.UTF_8);
            }

        } catch (IOException | TemplateException e) {
            throw new FailedToCreateResourceException("Failed to update pom dependency", e);
        }
    }


    private void addModule(Module module) throws IOException {
        String projectPomString = PomUtil.asString(module.getProjectPom());

        if (!projectPomString.contains("<module>" + module.getArtifactId() + "</module>")) {

            if (projectPomString.contains("<modules>")) {
                projectPomString = projectPomString.replace("</modules>", "\t<module>" + module.getArtifactId() + "</module>\n\t</modules>");
            } else {
                projectPomString = projectPomString.replace("</project>", "\n\t<modules>\n\t\t<module>" + module.getArtifactId() + "</module>\n\t</modules>\n</project>");
            }
            FileUtils.write(module.getProjectPom().getPomFile(), projectPomString, StandardCharsets.UTF_8);
        }

    }
}
