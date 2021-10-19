package org.dominokit.cli.generator.module.gwt;

import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.generator.exception.FailedToCreateResourceException;
import org.dominokit.cli.generator.Folder;
import org.dominokit.cli.generator.Package;
import org.dominokit.cli.generator.TemplateProvider;
import org.dominokit.cli.generator.TemplatedFile;
import org.dominokit.cli.generator.module.Module;
import org.dominokit.cli.generator.module.ModuleCreator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class GwtMultiModule implements ModuleCreator {
    public void create(Module module) throws IOException {
       create(module, "/module/gwt/multi"); 
    }

    public void create(Module module, String templatesBasePath) throws IOException {

        module
                .add(new Folder(module.getArtifactId()+"-backend").setCondition(module::isGenerateBackend)
                        .add(new Folder("src/main/java")
                                .add(new Package(module.getProject().getRootPackage())
                                        .add(new Package(module.getSubPackage()+".server")
                                                .add(new TemplatedFile("package-info.java", templatesBasePath + "/backend/source/package-info.java"))
                                        )
                                )
                        )
                        .add(new TemplatedFile("pom.xml", templatesBasePath + "/backend/pom.xml"))
                )
                .add(new Folder(module.getArtifactId()+"-frontend")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(module.getProject().getRootPackage())
                                                .add(new Package(module.getSubPackage()+".client")
                                                        .add(new Folder("presenters")
                                                                .add(new TemplatedFile(module.getPrefix()+"Proxy.java", templatesBasePath + "/frontend/source/Proxy.java"))
                                                        )
                                                        .add(new Folder("views")
                                                                .add(new TemplatedFile(module.getPrefix()+"View.java", templatesBasePath + "/frontend/source/View.java"))
                                                        )
                                                        .add(new TemplatedFile(module.getModuleName()+"ClientModule.java", templatesBasePath + "/frontend/source/ClientModule.java"))
                                                )
                                        )
                                )
                                .add(new TemplatedFile("module.gwt.xml", templatesBasePath + "/frontend/source/module.gwt.xml"))
                        )
                        .add(new Folder("src/test").setCondition(module::isGenerateTests)
                                .add(new Folder("java")
                                        .add(new Package(module.getProject().getRootPackage())
                                                .add(new Package(module.getSubPackage()+".client")
                                                        .add(new Folder("presenters")
                                                                .add(new TemplatedFile(module.getPrefix()+"PresenterSpy.java", templatesBasePath + "/frontend/test/PresenterSpy.java"))
                                                        )
                                                        .add(new Folder("views")
                                                                .add(new TemplatedFile("Fake"+module.getPrefix()+"View.java", templatesBasePath + "/frontend/test/FakeView.java"))
                                                        )
                                                        .add(new TemplatedFile(module.getPrefix()+"ProxyTest.java", templatesBasePath + "/frontend/test/ProxyTest.java"))
                                                        .add(new TemplatedFile(module.getModuleName()+"ClientModuleTestSuite.java", templatesBasePath + "/frontend/test/ClientModuleTestSuite.java"))
                                                )

                                        )
                                )
                        )
                        .add(new TemplatedFile("pom.xml", templatesBasePath + "/frontend/pom.xml"))
                )
                .add(new Folder(module.getArtifactId()+"-frontend-ui")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(module.getProject().getRootPackage())
                                                .add(new Package(module.getSubPackage()+".client")
                                                        .add(new Package("views.ui")
                                                                .add(new TemplatedFile(module.getPrefix()+"ViewImpl.java", templatesBasePath + "/ui/source/ViewImpl.java"))
                                                        )
                                                        .add(new TemplatedFile(module.getModuleName()+"UIClientModule.java", templatesBasePath + "/ui/source/UIClientModule.java"))
                                                )
                                        )
                                )
                                .add(new TemplatedFile("module.gwt.xml", templatesBasePath + "/ui/source/module.gwt.xml"))
                        )
                        .add(new TemplatedFile("pom.xml", templatesBasePath + "/ui/pom.xml"))
                )
                .add(new Folder(module.getArtifactId()+"-shared")
                        .add(new Folder("src/main/java")
                                .add(new Package(module.getProject().getRootPackage())
                                        .add(new Package(module.getSubPackage()+".shared")
                                                .add(new Folder("services")
                                                        .add(new TemplatedFile(module.getPrefix()+"Service.java", templatesBasePath + "/shared/source/Service.java"))
                                                )
                                        )
                                )
                        )
                        .add(new TemplatedFile("pom.xml", templatesBasePath + "/shared/pom.xml"))
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
                frontendDependency = TemplateProvider.render("/module/gwt/multi/frontend-dependency.ftl", module.context());
            }
            if (!frontEndPomString.contains("<artifactId>" + module.getArtifactId() + "-frontend-ui</artifactId>")) {
                frontendUiDependency = TemplateProvider.render("/module/gwt/multi/frontend-ui-dependency.ftl", module.context());
            }

            frontEndPomString = frontEndPomString.replace("</dependencies>", frontendDependency+frontendUiDependency+"\t</dependencies>");
            FileUtils.write(module.getFrontendPom().getPomFile(), frontEndPomString, StandardCharsets.UTF_8);

            String backendEndPomString = PomUtil.asString(module.getBackendPom());

            if(module.isGenerateBackend()) {
                String backendDependency = "";
                if (!backendEndPomString.contains("<artifactId>" + module.getArtifactId() + "-backend</artifactId>")) {
                    backendDependency = TemplateProvider.render("/module/gwt/multi/backend-dependency.ftl", module.context());
                }

                backendEndPomString = backendEndPomString.replace("</dependencies>", backendDependency + "\t</dependencies>");
                FileUtils.write(module.getBackendPom().getPomFile(), backendEndPomString, StandardCharsets.UTF_8);
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
