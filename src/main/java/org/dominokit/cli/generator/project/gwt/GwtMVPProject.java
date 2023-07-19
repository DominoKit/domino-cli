package org.dominokit.cli.generator.project.gwt;

import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.generator.Folder;
import org.dominokit.cli.generator.Package;
import org.dominokit.cli.generator.ResourceFile;
import org.dominokit.cli.generator.TemplatedFile;
import org.dominokit.cli.generator.module.ApiModuleFactory;
import org.dominokit.cli.generator.project.Project;
import org.dominokit.cli.generator.project.ProjectCreator;

import java.nio.file.Paths;

public class GwtMVPProject implements ProjectCreator {

    public void create(Project project) {
        project
                .add(ApiModuleFactory.create(project))
                .add(new Folder(".idea")
                        .add(new Folder("runConfigurations")
                                .add(new TemplatedFile("ALL_TESTS.xml", "/app/gwt/mvp/runConfigurations/ALL_TESTS.xml"))
                                .add(new TemplatedFile(project.getName() + ".xml", "/app/gwt/mvp/runConfigurations/app.xml"))
                                .add(new TemplatedFile(project.getName() + "-api.xml", "/app/gwt/mvp/runConfigurations/backend-api.xml"))
                                .add(new TemplatedFile(project.getName() + "-dev.xml", "/app/gwt/mvp/runConfigurations/app-dev.xml"))
                                .add(new TemplatedFile("Development.xml", "/app/gwt/mvp/runConfigurations/Development.xml"))
                                .add(new TemplatedFile("process_sources.xml", "/app/gwt/mvp/runConfigurations/process_sources.xml"))
                                .add(new TemplatedFile("super_dev_mode.xml", "/app/gwt/mvp/runConfigurations/super_dev_mode.xml"))
                        )
                )
                .add(new Folder(project.getName() + "-backend")
                        .add(new Folder("src/main/resources")
                                .add(new Folder(project.getModuleShortName())
                                        .add(new TemplatedFile(project.getName() + ".css", "/app/gwt/mvp/backend/resource/app.css"))
                                        .add(new TemplatedFile("index.html", "/app/gwt/mvp/backend/resource/index.html"))
                                        .add(new ResourceFile("favicon.ico", "/app/gwt/mvp/backend/resource/favicon.ico"))
                                        .add(new ResourceFile("favicon.png", "/app/gwt/mvp/backend/resource/favicon.png"))
                                )
                                .add(new TemplatedFile("config.json", "/app/gwt/mvp/backend/resource/config.json"))
                        )
                        .add(new TemplatedFile("pom.xml", "/app/gwt/mvp/backend/pom.xml"))
                )
                .add(new Folder(project.getName() + "-frontend")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(project.getRootPackage())
                                                .add(new TemplatedFile("AppClientModule.java", "/app/gwt/mvp/frontend/source/AppClientModule.java"))
                                        )
                                )
                                .add(new TemplatedFile("module.gwt.xml", "/app/gwt/mvp/frontend/source/module.gwt.xml"))
                        )
                        .add(new TemplatedFile("pom.xml", "/app/gwt/mvp/frontend/pom.xml"))
                )
                .add(new TemplatedFile("pom.xml", "/app/gwt/mvp/pom.ftl"))
                .add(new TemplatedFile("README.md", "/app/gwt/mvp/README.md"))
                .write(Paths.get(PathUtils.getUserDir()).toString(), project.context());
    }
}
