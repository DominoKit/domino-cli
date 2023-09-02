<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>
    <packaging>pom</packaging>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <vertx.version>${vertx_version}</vertx.version>
        <domino.history.version>${domino_history_version}</domino.history.version>
        <domino.rest.version>${domino_rest_version}</domino.rest.version>
        <domino.ui.version>${domino_ui_version}</domino.ui.version>
        <domino.jackson.version>${domino_jackson_version}</domino.jackson.version>
        <domino.mvp.version>${domino_mvp_version}</domino.mvp.version>
        <j2cl.maven.plugin.version>${j2cl_maven_plugin_version}</j2cl.maven.plugin.version>
    </properties>

    <repositories>
        <repository>
            <id>sonatype-snapshots-repo</id>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
                <checksumPolicy>fail</checksumPolicy>
            </snapshots>
        </repository>
    </repositories>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-mvp</artifactId>
                <version>${r"${domino.mvp.version}"}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-ui</artifactId>
                <version>${r"${domino.ui.version}"}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>

    </dependencies>

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.7.0</version>
                </plugin>
                <plugin>
                    <groupId>org.apache.tomcat.maven</groupId>
                    <artifactId>tomcat7-maven-plugin</artifactId>
                    <version>2.2</version>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-source-plugin</artifactId>
                    <version>3.0.1</version>
                    <executions>
                        <execution>
                            <id>attach-sources</id>
                            <goals>
                                <goal>jar-no-fork</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </pluginManagement>

        <plugins>
            <plugin>
                <groupId>com.vertispan.j2cl</groupId>
                <artifactId>j2cl-maven-plugin</artifactId>
                <inherited>false</inherited>
                <version>${r"${j2cl.maven.plugin.version}"}</version>
                <configuration>
                    <webappDirectory>${r"${project.build.directory}"}/gwt/launcherDir/app</webappDirectory>
                </configuration>
            </plugin>
        </plugins>

    </build>

    <modules>
        <module>${artifactId}-frontend</module>
        <module>${artifactId}-backend</module>
        <#if generateApi>
        <module>${artifactId}-api</module>
        </#if>
    </modules>
</project>
