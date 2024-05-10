package org.dominokit.cli.generator.module;

import org.apache.maven.model.Model;
import org.dominokit.cli.NameUtil;
import org.dominokit.cli.PomUtil;
import org.dominokit.cli.generator.Folder;
import org.dominokit.cli.generator.project.Project;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Module extends Folder {
    private String name;
    private Project project;
    private String artifactId;
    private String subPackage;
    private String prefix;
    private Model projectPom;
    private Model backendPom;
    private Model frontendPom;
    private boolean generateTests;
    private String compiler;
    private boolean generateBackend = true;

    public Module(String name) {
        super("", name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getModuleName(){
        return NameUtil.capitalizedName(name);
    }
    public String getModulePackage(){
        return name.toLowerCase();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getArtifactId() {
        if(isNull(artifactId) || artifactId.isEmpty()){
            return name;
        }
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getSubPackage() {
        if (isNull(subPackage) || subPackage.trim().isEmpty()) {
            return name.toLowerCase()
                    .replace("_", ".")
                    .replace("-", ".");
        }
        return subPackage;
    }

    public void setSubPackage(String subPackage) {
        this.subPackage = subPackage;
    }

    public String getPrefix() {
        if(isNull(prefix) || prefix.isEmpty()){
            return getModuleName();
        }
        return NameUtil.capitalizedName(prefix);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Model getProjectPom() {
        return projectPom;
    }

    public Model getBackendPom() {
        return backendPom;
    }

    public Model getFrontendPom() {
        return frontendPom;
    }

    public boolean isGenerateTests() {
        return generateTests;
    }

    public void setGenerateTests(boolean generateTests) {
        this.generateTests = generateTests;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public boolean isGenerateBackend() {
        return generateBackend;
    }

    public void setGenerateBackend(boolean generateBackend) {
        this.generateBackend = generateBackend;
    }

    public Module init() {
        try {
            Model projectPom = PomUtil.asModel("");

            String artifactId = projectPom.getArtifactId();

            Model frontendPom = PomUtil.asModel(artifactId + "-frontend");
            Model backendPom = PomUtil.asModel(artifactId + "-backend");

            Project project = new Project(projectPom.getPomFile().getParent());

            project.setName(artifactId);
            if (nonNull(projectPom.getParent())) {
                project.setGroupId(projectPom.getParent().getGroupId());
                project.setRootPackage(projectPom.getParent().getGroupId());
                project.setVersion(projectPom.getParent().getVersion());
            } else {
                project.setGroupId(projectPom.getGroupId());
                project.setRootPackage(projectPom.getGroupId());
                project.setVersion(projectPom.getVersion());
            }
            project.setArtifactId(projectPom.getArtifactId());
            this.project = project;
            this.projectPom = projectPom;
            this.backendPom = backendPom;
            this.frontendPom = frontendPom;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public Map<String, Object> context() {

        Map<String, Object> context = new HashMap<>(project.context());
        context.put("artifactId", getArtifactId());
        context.put("rootArtifactId", project.getArtifactId());
        context.put("subpackage", getSubPackage());
        context.put("moduleName", getModuleName());
        context.put("prefix", getPrefix());
        context.put("token", getPrefix().toLowerCase());
        context.put("projectPom", projectPom);
        context.put("backendPom", backendPom);
        context.put("frontendPom", frontendPom);
        context.put("generateTests", generateTests);
        context.put("compiler", compiler);
        context.put("generateBackend", generateBackend);
        context.put("modulePackage", getModulePackage());

        return context;
    }

    @Override
    public String toString() {
        return "Module{" +
                "\n\t name='" + name + '\'' +
                "\n\t project=" + project +
                "\n\t artifactId='" + getArtifactId() + '\'' +
                "\n\t subPackage='" + getSubPackage() + '\'' +
                "\n\t prefix='" + getPrefix() + '\'' +
                "\n\t projectPom='" + projectPom.getGroupId() +":"+project.getArtifactId() + '\'' +
                "\n\t backendPom='" + backendPom.getGroupId() +":"+backendPom.getArtifactId() + '\'' +
                "\n\t frontendPom='" + frontendPom.getGroupId() +":"+frontendPom.getArtifactId() + '\'' +
                "\n\t generateTests=" + generateTests +
                "\n\t compiler='" + compiler + '\'' +
                "\n\t generateBackend=" + generateBackend +
                "\n\t modulePackage=" + getModulePackage() +
                '}';
    }
}
