<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>${groupId}</groupId>
        <artifactId>${rootArtifactId}</artifactId>
        <version>${version}</version>
    </parent>

    <artifactId>${artifactId}</artifactId>
    <packaging>pom</packaging>

    <name>${artifactId}</name>
    <url>http://maven.apache.org</url>

    <dependencies>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

    <modules>
        <#if generateBackend>
        <module>${artifactId}-backend</module>
        </#if>
        <module>${artifactId}-frontend</module>
        <module>${artifactId}-frontend-ui</module>
        <module>${artifactId}-shared</module>
    </modules>

</project>
