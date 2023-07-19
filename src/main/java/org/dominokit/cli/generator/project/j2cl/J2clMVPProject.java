package org.dominokit.cli.generator.project.j2cl;

import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.generator.Folder;
import org.dominokit.cli.generator.Package;
import org.dominokit.cli.generator.ResourceFile;
import org.dominokit.cli.generator.TemplatedFile;
import org.dominokit.cli.generator.module.ApiModuleFactory;
import org.dominokit.cli.generator.project.Project;
import org.dominokit.cli.generator.project.ProjectCreator;

import java.nio.file.Paths;

public class J2clMVPProject implements ProjectCreator {

    public void create(Project project){
        project
                .add(ApiModuleFactory.create(project))
                .add(new Folder(".idea")
                        .add(new Folder("runConfigurations")
                                .add(new TemplatedFile("ALL_TESTS.xml", "/app/j2cl/mvp/runConfigurations/ALL_TESTS.xml"))
                                .add(new TemplatedFile(project.getName()+".xml", "/app/j2cl/mvp/runConfigurations/app.xml"))
                                .add(new TemplatedFile(project.getName()+"-api.xml", "/app/j2cl/mvp/runConfigurations/backend-api.xml"))
                                .add(new TemplatedFile(project.getName()+"-dev.xml", "/app/j2cl/mvp/runConfigurations/app-dev.xml"))
                                .add(new TemplatedFile("Development.xml", "/app/j2cl/mvp/runConfigurations/Development.xml"))
                                .add(new TemplatedFile("process_sources.xml", "/app/j2cl/mvp/runConfigurations/process_sources.xml"))
                                .add(new TemplatedFile("super_dev_mode.xml", "/app/j2cl/mvp/runConfigurations/super_dev_mode.xml"))
                        )
                )
                .add(new Folder(project.getName()+"-backend")
                        .add(new Folder("src/main/resources")
                                .add(new Folder(project.getModuleShortName())
                                        .add(new TemplatedFile(project.getName()+".css", "/app/j2cl/mvp/backend/resource/app.css"))
                                        .add(new TemplatedFile("index.html", "/app/j2cl/mvp/backend/resource/index.html"))
                                        .add(new ResourceFile("favicon.ico", "/app/j2cl/mvp/backend/resource/favicon.ico"))
                                        .add(new ResourceFile("favicon.png", "/app/j2cl/mvp/backend/resource/favicon.png"))
                                )
                                .add(new TemplatedFile("config.json", "/app/j2cl/mvp/backend/resource/config.json"))
                        )
                        .add(new TemplatedFile("pom.xml", "/app/j2cl/mvp/backend/pom.xml"))
                )
                .add(new Folder(project.getName()+"-frontend")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(project.getRootPackage())
                                                .add(new TemplatedFile("AppClientModule.java", "/app/j2cl/mvp/frontend/source/AppClientModule.java"))
                                                .add(new TemplatedFile("AppClientModule.native.js", "/app/j2cl/mvp/frontend/source/AppClientModule.native.js"))
                                        )
                                        .add(new Package("com.google.gwt.core.client")
                                                .add(new TemplatedFile("EntryPoint.java", "/app/j2cl/mvp/frontend/source/EntryPoint.java"))
                                        )
                                )
                                .add(new Folder("webapp/WEB-INF")
                                        .add(new TemplatedFile("web.xml", "/app/j2cl/mvp/frontend/webapp/web.xml"))
                                )
                        )
                        .add(new TemplatedFile("pom.xml", "/app/j2cl/mvp/frontend/pom.xml"))
                )
                .add(new TemplatedFile("pom.xml", "/app/j2cl/mvp/pom.ftl"))
                .add(new TemplatedFile("README.md", "/app/j2cl/mvp/README.md"))
                .write(Paths.get(PathUtils.getUserDir()).toString(), project.context());
    }
}
