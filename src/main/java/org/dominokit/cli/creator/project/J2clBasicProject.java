package org.dominokit.cli.creator.project;

import org.dominokit.cli.commands.PathUtils;
import org.dominokit.cli.creator.Folder;
import org.dominokit.cli.creator.Package;
import org.dominokit.cli.creator.Project;
import org.dominokit.cli.creator.ResourceFile;
import org.dominokit.cli.creator.TemplatedFile;
import org.dominokit.cli.creator.module.ApiModuleFactory;

import java.nio.file.Paths;

public class J2clBasicProject implements ProjectCreator{

    public void create(Project project){
        project
                .add(ApiModuleFactory.create(project))
                .add(new Folder(project.getName()+"-client")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(project.getRootPackage())
                                                .add(new TemplatedFile("App.java", "/app/j2cl/basic/client/source/App.java"))
                                                .add(new TemplatedFile("App.native.js", "/app/j2cl/basic/client/source/App.native.js"))
                                        )
                                        .add(new Package("com.google.gwt.core.client")
                                                .add(new TemplatedFile("EntryPoint.java", "/app/j2cl/basic/client/source/EntryPoint.java"))
                                        )
                                )
                                .add(new Folder("webapp/WEB-INF")
                                        .add(new TemplatedFile("web.xml", "/app/j2cl/basic/server/webapp/web.xml"))
                                )
                        )
                        .add(new TemplatedFile("pom.xml", "/app/j2cl/basic/client/pom.xml"))
                )
                .add(new Folder(project.getName()+"-server")
                        .add(new Folder("src/main")
                                .add(new Folder("jettyconf")
                                        .add(new TemplatedFile("context.xml", "/app/j2cl/basic/server/jetty/context.xml"))
                                )
                                .add(new Folder("tomcatconf")
                                        .add(new TemplatedFile("context.xml", "/app/j2cl/basic/server/tomcat/context.xml"))
                                )
                                .add(new Folder("webapp")
                                        .add(new Folder("WEB-INF")
                                                .add(new TemplatedFile("web.xml", "/app/j2cl/basic/server/webapp/web.xml"))
                                        )
                                        .add(new TemplatedFile(project.getName()+"-ui-starter.css", "/app/j2cl/basic/server/webapp/ui-starter.css" ))
                                        .add(new TemplatedFile("index.html", "/app/j2cl/basic/server/webapp/index.html" ))
                                        .add(new ResourceFile("favicon.ico", "/app/j2cl/basic/server/webapp/favicon.ico" ))
                                        .add(new ResourceFile("favicon.png", "/app/j2cl/basic/server/webapp/favicon.png" ))
                                )
                        )
                        .add(new TemplatedFile("pom.xml", "/app/j2cl/basic/server/pom.xml"))
                )
                .add(new Folder(project.getName()+"-shared")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(project.getRootPackage())
                                                .add(new TemplatedFile("Person.java", "/app/j2cl/basic/shared/source/Person.java"))
                                        )
                                )
                        )
                        .add(new TemplatedFile("pom.xml", "/app/j2cl/basic/shared/pom.xml"))
                )
                .add(new TemplatedFile("pom.xml", "/app/j2cl/basic/pom.ftl"))
                .add(new TemplatedFile("README.md", "/app/j2cl/basic/README.md"))
                .write(Paths.get(PathUtils.getUserDir()).toString(), project.context());
    }
}
