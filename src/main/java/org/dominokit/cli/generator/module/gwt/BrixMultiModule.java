package org.dominokit.cli.generator.module.gwt;

import freemarker.template.TemplateException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.generator.Folder;
import org.dominokit.cli.generator.Package;
import org.dominokit.cli.generator.TemplateProvider;
import org.dominokit.cli.generator.TemplatedFile;
import org.dominokit.cli.generator.exception.FailedToCreateResourceException;
import org.dominokit.cli.generator.module.Module;
import org.dominokit.cli.generator.module.ModuleCreator;

public class BrixMultiModule implements ModuleCreator {
    public void create(Module module) throws IOException {
       create(module, "/module/gwt/multi/brix");
    }

    public void create(Module module, String templatesBasePath) throws IOException {

        module
                .add(new Folder(module.getArtifactId()+"-frontend")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(module.getProject().getRootPackage())
                                            .add(new Package(module.getModulePackage())
                                                .add(new Package("presenters."+module.getSubPackage())
                                                    .add(new TemplatedFile(module.getPrefix()+"Presenter.java", templatesBasePath + "/frontend/source/Presenter.java"))
                                                )
                                                .add(new Package("tasks."+module.getSubPackage())
                                                    .add(new TemplatedFile(module.getPrefix()+"StartupTask.java", templatesBasePath + "/frontend/source/StartupTask.java"))
                                                )
                                                .add(new Package("views."+module.getSubPackage())
                                                    .add(new TemplatedFile(module.getPrefix()+"View.java", templatesBasePath + "/frontend/source/View.java"))
                                                )
                                                .add(new TemplatedFile("package-info.java", templatesBasePath + "/frontend/source/package-info.java"))
                                            )
                                        )
                                )
                                .add(new TemplatedFile("module.gwt.xml", templatesBasePath + "/frontend/source/module.gwt.xml"))
                        )
                        .add(new TemplatedFile("pom.xml", templatesBasePath + "/frontend/pom.ftl"))
                )
                .add(new Folder(module.getArtifactId()+"-ui")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(module.getProject().getRootPackage())
                                            .add(new Package(module.getModulePackage())
                                                .add(new Package("ui.views")
                                                    .add(new Package(module.getSubPackage())
                                                        .add(new TemplatedFile(module.getPrefix()+"ViewImpl.java", templatesBasePath + "/frontend-ui/source/ViewImpl.java"))
                                                    )
                                                )
                                                .add(new TemplatedFile(module.getModuleName()+"Component.java", templatesBasePath + "/frontend-ui/source/Component.java"))
                                                .add(new TemplatedFile("package-info.java", templatesBasePath + "/frontend-ui/source/package-info.java"))
                                            )
                                        )
                                )
                                .add(new TemplatedFile("module.gwt.xml", templatesBasePath + "/frontend-ui/source/module.gwt.xml"))
                        )
                        .add(new TemplatedFile("pom.xml", templatesBasePath + "/frontend-ui/pom.ftl"))
                )
                .add(new Folder(module.getArtifactId()+"-shared")
                        .add(new Folder("src/main/java")
                                .add(new Package(module.getProject().getRootPackage())
                                    .add(new Package(module.getModulePackage())
                                        .add(new Package("shared." + module.getSubPackage())
                                            .add(new TemplatedFile(module.getPrefix()+"Event.java", templatesBasePath + "/shared/source/Event.java"))
                                        )
                                    )
                                )
                        )
                        .add(new TemplatedFile("pom.xml", templatesBasePath + "/shared/pom.ftl"))
                )
                .add(new TemplatedFile("pom.xml", templatesBasePath + "/pom.ftl"))
                .write(Paths.get(PathUtils.getUserDir()).toString(), module.context());

        addDependency(module);
        addModule(module);
    }

    private void addDependency(Module module) {
        try {
            String frontEndPomString = PomUtil.asString(module.getFrontendPom());

            String frontendDependency="";
            String frontendUiDependency="";
            if (!frontEndPomString.contains("<artifactId>" + module.getArtifactId() + "-frontend</artifactId>")) {
                frontendDependency = TemplateProvider.render("/module/gwt/multi/brix/frontend-dependency.ftl", module.context());
            }
            if (!frontEndPomString.contains("<artifactId>" + module.getArtifactId() + "-ui</artifactId>")) {
                frontendUiDependency = TemplateProvider.render("/module/gwt/multi/brix/ui-dependency.ftl", module.context());
            }

            frontEndPomString = frontEndPomString.replace("</dependencies>", frontendDependency+frontendUiDependency+"\t</dependencies>");
            FileUtils.write(module.getFrontendPom().getPomFile(), frontEndPomString, StandardCharsets.UTF_8);

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
