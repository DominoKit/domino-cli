<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>
  </parent>

  <artifactId>${artifactId}-frontend</artifactId>
  <name>${artifactId}-frontend</name>
  <packaging>gwt-lib</packaging>

  <dependencies>
    <dependency>
      <groupId>org.dominokit</groupId>
      <artifactId>domino-brix-shared</artifactId>
    </dependency>
    <dependency>
      <groupId>com.google.gwt</groupId>
      <artifactId>gwt-user</artifactId>
    </dependency>
    <dependency>
      <groupId>com.google.gwt</groupId>
      <artifactId>gwt-dev</artifactId>
    </dependency>
    <dependency>
      <groupId>org.dominokit</groupId>
      <artifactId>domino-brix-client</artifactId>
    </dependency>
    <dependency>
      <groupId>org.dominokit</groupId>
      <artifactId>domino-brix-processor</artifactId>
      <version>${r"${domino.brix.version}"}</version>
      <scope>provided</scope>
    </dependency>
    <dependency>
      <groupId>org.dominokit</groupId>
      <artifactId>domino-rest-client</artifactId>
      <version>${r"${domino.rest.version}"}</version>
    </dependency>
    <dependency>
      <groupId>org.dominokit</groupId>
      <artifactId>domino-rest-processor</artifactId>
      <version>${r"${domino.rest.version}"}</version>
    </dependency>
    <dependency>
      <groupId>${groupId}</groupId>
      <artifactId>${artifactId}-shared</artifactId>
      <version>${r"${project.version}"}</version>
    </dependency>
    <dependency>
      <groupId>${groupId}</groupId>
      <artifactId>${artifactId}-shared</artifactId>
      <version>${r"${project.version}"}</version>
      <classifier>sources</classifier>
    </dependency>

    <dependency>
      <groupId>com.google.dagger</groupId>
      <artifactId>dagger-compiler</artifactId>
      <version>2.49</version>
      <scope>provided</scope>
    </dependency>

  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>net.ltgt.gwt.maven</groupId>
        <artifactId>gwt-maven-plugin</artifactId>
        <configuration>
          <moduleName>${rootPackage}.${subpackage}.${moduleName}</moduleName>
          <moduleShortName>${moduleName}</moduleShortName>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-source-plugin</artifactId>
      </plugin>
    </plugins>
  </build>

</project>
