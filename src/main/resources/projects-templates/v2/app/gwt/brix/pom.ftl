<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <#if hasParent>

    <parent>
        <groupId>${groupId}</groupId>
        <artifactId>${parentArtifactId}</artifactId>
        <version>${version}</version>
    </parent>
    <artifactId>${artifactId}</artifactId>

    <#else>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>

    </#if>
    <packaging>pom</packaging>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>

        <resteasy.version>4.7.7.Final</resteasy.version>

        <gwt.version>${gwt_version}</gwt.version>
        <domino.rest.version>${domino_rest_version}</domino.rest.version>
        <domino.ui.version>${domino_ui_version}</domino.ui.version>
        <domino.jackson.version>${domino_jackson_version}</domino.jackson.version>
        <domino.auto.version>${domino_auto_version}</domino.auto.version>
        <domino.brix.version>${domino_brix_version}</domino.brix.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.gwtproject</groupId>
                <artifactId>gwt</artifactId>
                <version>${r"${gwt.version}"}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>javax.servlet</groupId>
                <artifactId>javax.servlet-api</artifactId>
                <version>3.1.0</version>
            </dependency>
            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-brix-client</artifactId>
                <version>${r"${domino.brix.version}"}</version>
            </dependency>
            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-brix-shared</artifactId>
                <version>${r"${domino.brix.version}"}</version>
            </dependency>
            <dependency>
                <groupId>com.google.dagger</groupId>
                <artifactId>dagger-compiler</artifactId>
                <version>2.49</version>
                <scope>provided</scope>
            </dependency>
            <dependency>
                <groupId>com.google.auto.service</groupId>
                <artifactId>auto-service</artifactId>
                <version>1.1.1</version>
                <scope>provided</scope>
            </dependency>
            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-brix-processor</artifactId>
                <version>${r"${domino.brix.version}"}</version>
                <scope>provided</scope>
            </dependency>

            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-ui</artifactId>
                <version>${r"${domino.ui.version}"}</version>
            </dependency>
            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-ui</artifactId>
                <version>${r"${domino.ui.version}"}</version>
                <classifier>sources</classifier>
            </dependency>
            <dependency>
                <groupId>org.dominokit</groupId>
                <artifactId>domino-auto-processor</artifactId>
                <version>${r"${domino.auto.version}"}</version>
                <scope>provided</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>net.ltgt.gwt.maven</groupId>
                <artifactId>gwt-maven-plugin</artifactId>
                <inherited>false</inherited>
                <configuration>
                    <launcherDir>${r"${project.build.directory}"}/gwt/launcherDir</launcherDir>
                </configuration>
            </plugin>
        </plugins>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.11.0</version>
                    <configuration>
                        <source>${r"${maven.compiler.source}"}</source>
                        <target>${r"${maven.compiler.target}"}</target>
                        <compilerArgs>
                            <arg>-AdominoAutoInclude=org.dominokit.brix</arg>
                        </compilerArgs>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.eclipse.jetty</groupId>
                    <artifactId>jetty-maven-plugin</artifactId>
                    <version>9.4.44.v20210927</version>
                </plugin>
                <plugin>
                    <groupId>net.ltgt.gwt.maven</groupId>
                    <artifactId>gwt-maven-plugin</artifactId>
                    <version>1.1.0</version>
                    <extensions>true</extensions>
                    <configuration>
                        <sourceLevel>${r"${maven.compiler.source}"}</sourceLevel>
                        <failOnError>true</failOnError>
                    </configuration>
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

    <modules>
        <module>${name}-backend</module>
        <module>${name}-frontend</module>
        <module>${name}-shared</module>
    </modules>
</project>
