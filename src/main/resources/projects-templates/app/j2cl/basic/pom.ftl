<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>
    <packaging>pom</packaging>

    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <domino.ui.version>${domino_ui_version}</domino.ui.version>
        <j2cl.maven.plugin.version>${j2cl_maven_plugin_version}</j2cl.maven.plugin.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>javax.servlet</groupId>
                <artifactId>javax.servlet-api</artifactId>
                <version>3.1.0</version>
                <scope>provided</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>com.vertispan.j2cl</groupId>
                <artifactId>j2cl-maven-plugin</artifactId>
                <inherited>false</inherited>
                <configuration>
                    <webappDirectory>${r"${project.build.directory}"}/gwt/launcherDir</webappDirectory>
                </configuration>
            </plugin>
        </plugins>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <!-- Do not upgrade past 3.1 to avoid triggering https://issues.apache.org/jira/browse/MSOURCES-95 -->
                    <!-- Unless you use annotation processors, then upgrade to 3.5.1 at a minimum -->
                    <version>3.5.1</version>
                    <configuration>
                        <source>${r"${maven.compiler.source}"}</source>
                        <target>${r"${maven.compiler.target}"}</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.eclipse.jetty</groupId>
                    <artifactId>jetty-maven-plugin</artifactId>
                    <version>9.4.8.v20171121</version>
                </plugin>
                <plugin>
                    <groupId>com.vertispan.j2cl</groupId>
                    <artifactId>j2cl-maven-plugin</artifactId>
                    <version>${r"${j2cl.maven.plugin.version}"}</version>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-source-plugin</artifactId>
                    <version>3.0.1</version>
                    <executions>
                        <execution>
                            <id>attach-sources</id>
                            <phase>package</phase>
                            <goals>
                                <goal>jar-no-fork</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
                <plugin>
                    <groupId>org.apache.tomcat.maven</groupId>
                    <artifactId>tomcat7-maven-plugin</artifactId>
                    <version>2.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
    <repositories>
        <repository>
            <id>vertispan-releases</id>
            <name>Vertispan hosted artifacts-releases</name>
            <url>https://repo.vertispan.com/j2cl/</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
        <repository>
            <id>sonatype-snapshots-repo</id>
            <url>https://oss.sonatype.org/content/repositories/snapshots</url>
            <snapshots>
                <updatePolicy>always</updatePolicy>
                <checksumPolicy>fail</checksumPolicy>
            </snapshots>
        </repository>
    </repositories>

    <modules>
        <module>${name}-client</module>
        <module>${name}-shared</module>
        <module>${name}-server</module>
        <#if generateApi>
        <module>${name}-api</module>
        </#if>
    </modules>
</project>