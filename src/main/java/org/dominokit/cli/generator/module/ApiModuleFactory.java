package org.dominokit.cli.generator.module;

import org.dominokit.cli.generator.Folder;
import org.dominokit.cli.generator.Package;
import org.dominokit.cli.generator.project.Project;
import org.dominokit.cli.generator.TemplatedFile;

public class ApiModuleFactory {

    public static Folder create(Project project){
        return new Folder(project.getName()+"-api").setCondition(project::isGenerateApi)
                .add(new Folder("src/main")
                        .add(new Folder("docker")
                                .add(new TemplatedFile("Dockerfile.jvm", "/api/docker/Dockerfile.jvm"))
                                .add(new TemplatedFile("Dockerfile.legacy-jar", "/api/docker/Dockerfile.legacy-jar"))
                                .add(new TemplatedFile("Dockerfile.native", "/api/docker/Dockerfile.native"))
                                .add(new TemplatedFile("Dockerfile.native-distroless", "/api/docker/Dockerfile.native-distroless"))
                        )
                        .add(new Folder("java")
                                .add(new Package(project.getRootPackage())
                                        .add(new TemplatedFile("GreetingResource.java", "/api/source/GreetingResource.java"))
                                )
                        )
                        .add(new Folder("resources")
                                .add(new Folder("META-INF")
                                        .add(new Folder("resources")
                                                .add(new TemplatedFile("index.html", "/api/resource/index.html"))
                                        )
                                )
                                .add(new TemplatedFile("application.properties", "/api/resource/application.properties"))
                        )
                )
                .add(new TemplatedFile(".dockerignore", "/api/dockerignore"))
                .add(new TemplatedFile("pom.xml", "/api/pom.xml"))
                .add(new TemplatedFile("README.md", "/api/README.md"));
    }
}
