<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>${groupId}</groupId>
    <artifactId>${rootArtifactId}</artifactId>
    <version>${version}</version>
  </parent>

  <artifactId>${artifactId}</artifactId>
  <packaging>pom</packaging>

  <modules>
    <module>${artifactId}-frontend</module>
    <module>${artifactId}-shared</module>
    <module>${artifactId}-ui</module>
  </modules>

</project>
