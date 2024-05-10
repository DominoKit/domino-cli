package org.dominokit.cli.generator.project.gwt;

import java.nio.file.Paths;
import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.generator.Folder;
import org.dominokit.cli.generator.Package;
import org.dominokit.cli.generator.ResourceFile;
import org.dominokit.cli.generator.TemplatedFile;
import org.dominokit.cli.generator.module.ApiModuleFactory;
import org.dominokit.cli.generator.project.Project;
import org.dominokit.cli.generator.project.ProjectCreator;

public class GwtBrixProject implements ProjectCreator {

  public void create(Project project) {
    project
        .add(new Folder(".idea")
            .add(new Folder("runConfigurations")
                .add(new TemplatedFile("Frontend.xml",
                    "/app/gwt/brix/runConfigurations/Frontend.xml"))
                .add(new TemplatedFile("Server.xml", "/app/gwt/brix/runConfigurations/Server.xml"))
                .add(new TemplatedFile("Development.xml",
                    "/app/gwt/brix/runConfigurations/Development.xml"))
            )
        )
        .add(new Folder(project.getName() + "-backend")
            .add(new Folder("src/main")
                .add(new Folder("docker")
                    .add(new TemplatedFile("Dockerfile.jvm", "/app/gwt/brix/backend/docker/Dockerfile.jvm"))
                    .add(new TemplatedFile("Dockerfile.legacy-jar", "/app/gwt/brix/backend/docker/Dockerfile.legacy-jar"))
                    .add(new TemplatedFile("Dockerfile.native", "/app/gwt/brix/backend/docker/Dockerfile.native"))
                    .add(new TemplatedFile("Dockerfile.native-distroless", "/app/gwt/brix/backend/docker/Dockerfile.native-distroless"))
                )
                .add(new Folder("java")
                    .add(new Package(project.getRootPackage())
                        .add(new TemplatedFile("ConfigResource.java", "/app/gwt/brix/backend/source/ConfigResource.java"))
                        .add(new TemplatedFile("IndexPageFilter.java", "/app/gwt/brix/backend/source/IndexPageFilter.java"))
                    )
                )
                .add(new Folder("resources")
                    .add(new TemplatedFile("application.properties",
                        "/app/gwt/brix/backend/resource/application.properties"))
                    .add(new Folder("META-INF")
                        .add(new Folder("resources")
                            .add(new ResourceFile("app.css",
                                "/app/gwt/brix/backend/resource/META-INF/resources/app.css"))
                            .add(new ResourceFile("favicon.ico",
                                "/app/gwt/brix/backend/resource/META-INF/resources/favicon.ico"))
                            .add(new ResourceFile("favicon.png",
                                "/app/gwt/brix/backend/resource/META-INF/resources/favicon.png"))
                            .add(new TemplatedFile("index.html",
                                "/app/gwt/brix/backend/resource/META-INF/resources/index.html"))
                        )
                    )
                )
            )
            .add(new TemplatedFile("pom.xml", "/app/gwt/brix/backend/pom.xml"))
            .add(new TemplatedFile("README.md", "/app/gwt/brix/backend/README.md"))
        )
        .add(new Folder(project.getName() + "-frontend")
            .add(new Folder("src/main")
                .add(new Folder("assembly")
                    .add(new TemplatedFile("webjar.xml", "/app/gwt/brix/frontend/source/webjar.xml"))
                )
                .add(new Folder("java")
                    .add(new Package(project.getRootPackage())
                        .add(new TemplatedFile("App.java",
                            "/app/gwt/brix/frontend/source/App.java"))
                        .add(new TemplatedFile("package-info.java",
                            "/app/gwt/brix/frontend/source/package-info.java"))
                    )
                )
                .add(new TemplatedFile("module.gwt.xml",
                    "/app/gwt/brix/frontend/source/module.gwt.xml"))
            )
            .add(new TemplatedFile("pom.xml", "/app/gwt/brix/frontend/pom.xml"))
        )
        .add(new Folder(project.getName() + "-shared")
            .add(new Folder("src/main/java")
                    .add(new Package(project.getRootPackage())
                        .add(new TemplatedFile("ConfigService.java",
                            "/app/gwt/brix/shared/source/ConfigService.java"))
                    )
            )
            .add(new TemplatedFile("pom.xml", "/app/gwt/brix/shared/pom.xml"))
        )
        .add(new TemplatedFile("pom.xml", "/app/gwt/brix/pom.ftl"))
        .add(new TemplatedFile("README.md", "/app/gwt/brix/README.md"))
        .write(Paths.get(PathUtils.getUserDir()).toString(), project.context());
  }
}
