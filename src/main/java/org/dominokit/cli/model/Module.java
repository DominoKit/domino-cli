package org.dominokit.cli.model;

import org.apache.maven.model.Model;
import org.apache.velocity.VelocityContext;
import org.dominokit.cli.NameUtil;

public class Module implements IsContext {

    private Project project;
    private String artifactId;
    private String subPackage;
    private String moduleName;
    private Model projectPom;
    private Model backendPom;
    private Model frontendPom;
    private boolean generateTests;
    private boolean j2cl;

    public Module(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
        this.moduleName = NameUtil.capitalizedName(artifactId);
    }

    public String getSubPackage() {
        return subPackage;
    }

    public void setSubPackage(String subPackage) {
        this.subPackage = subPackage;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Model getProjectPom() {
        return projectPom;
    }

    public void setProjectPom(Model projectPom) {
        this.projectPom = projectPom;
    }

    public Model getBackendPom() {
        return backendPom;
    }

    public void setBackendPom(Model backendPom) {
        this.backendPom = backendPom;
    }

    public Model getFrontendPom() {
        return frontendPom;
    }

    public void setFrontendPom(Model frontendPom) {
        this.frontendPom = frontendPom;
    }

    public boolean isJ2cl() {
        return j2cl;
    }

    public void setJ2cl(boolean j2cl) {
        this.j2cl = j2cl;
    }

    @Override
    public VelocityContext asContext(){
        VelocityContext context = project.asContext();
        context.put("rootArtifactId", project.getArtifactId());
        context.put("artifactId", artifactId);
        context.put("subpackage", subPackage);
        context.put("moduleName", moduleName);
        context.put("generateTests", generateTests);

        return context;
    }

    public void setGenerateTests(boolean generateTests) {
        this.generateTests = generateTests;
    }

    public boolean getGenerateTests() {
        return generateTests;
    }
}
