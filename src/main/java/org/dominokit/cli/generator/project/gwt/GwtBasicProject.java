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

/**
 * Generates a basic GWT project scaffold.
 */
public class GwtBasicProject implements ProjectCreator {

    /**
     * Creates the project structure for a basic GWT app.
     *
     * @param project project model
     */
    public void create(Project project){
        project
                .add(ApiModuleFactory.create(project))
                .add(new Folder(project.getName()+"-client")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(project.getRootPackage())
                                                .add(new TemplatedFile("App.java", "/app/gwt/basic/client/source/App.java"))
                                        )
                                )
                                .add(new TemplatedFile("module.gwt.xml", "/app/gwt/basic/client/source/module.gwt.xml"))
                        )
                        .add(new TemplatedFile("pom.xml", "/app/gwt/basic/client/pom.xml"))
                )
                .add(new Folder(project.getName()+"-server")
                        .add(new Folder("src/main")
                                .add(new Folder("jettyconf")
                                        .add(new TemplatedFile("context.xml", "/app/gwt/basic/server/jetty/context.xml"))
                                )
                                .add(new Folder("tomcatconf")
                                        .add(new TemplatedFile("context.xml", "/app/gwt/basic/server/tomcat/context.xml"))
                                )
                                .add(new Folder("webapp")
                                        .add(new Folder("WEB-INF")
                                                .add(new TemplatedFile("web.xml", "/app/gwt/basic/server/webapp/web.xml"))
                                        )
                                        .add(new TemplatedFile(project.getName()+"-ui-starter.css", "/app/gwt/basic/server/webapp/ui-starter.css" ))
                                        .add(new TemplatedFile("index.html", "/app/gwt/basic/server/webapp/index.html" ))
                                        .add(new ResourceFile("favicon.ico", "/app/gwt/basic/server/webapp/favicon.ico" ))
                                        .add(new ResourceFile("favicon.png", "/app/gwt/basic/server/webapp/favicon.png" ))
                                )
                        )
                        .add(new TemplatedFile("pom.xml", "/app/gwt/basic/server/pom.xml"))
                )
                .add(new Folder(project.getName()+"-shared")
                        .add(new Folder("src/main")
                                .add(new Folder("java")
                                        .add(new Package(project.getRootPackage())
                                                .add(new TemplatedFile("Person.java", "/app/gwt/basic/shared/source/Person.java"))
                                        )
                                )
                        )
                        .add(new TemplatedFile("pom.xml", "/app/gwt/basic/shared/pom.xml"))
                )
                .add(new TemplatedFile("pom.xml", "/app/gwt/basic/pom.ftl"))
                .add(new TemplatedFile("README.md", "/app/gwt/basic/README.md"))
                .write(Paths.get(PathUtils.getUserDir()).toString(), project.context());
    }
}
