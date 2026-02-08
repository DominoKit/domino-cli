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

/**
 * Model representing a generated module and its template context.
 */
public class Module extends Folder {
    private String name;
    private Project project;
    private String artifactId;
    private String subPackage;
    private String prefix;
    private Model projectPom;
    private Model backendPom;
    private Model frontendPom;
    private String compiler;

    /**
     * Creates a module folder rooted at the module name.
     *
     * @param name module name
     */
    public Module(String name) {
        super("", name);
        this.name = name;
    }

    /**
     * Returns the module name.
     *
     * @return module name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the module name in PascalCase.
     *
     * @return module name in PascalCase
     */
    public String getModuleName(){
        return NameUtil.capitalizedName(name);
    }

    /**
     * Returns the module package name in lowercase.
     *
     * @return module package
     */
    public String getModulePackage(){
        return name.toLowerCase();
    }

    /**
     * Returns the owning project model.
     *
     * @return project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Sets the owning project model.
     *
     * @param project project model
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Returns the module artifactId, defaulting to the name.
     *
     * @return artifactId
     */
    public String getArtifactId() {
        if(isNull(artifactId) || artifactId.isEmpty()){
            return name;
        }
        return artifactId;
    }

    /**
     * Sets the module artifactId.
     *
     * @param artifactId artifactId
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Returns the module subpackage name.
     *
     * @return subpackage
     */
    public String getSubPackage() {
        if (isNull(subPackage) || subPackage.trim().isEmpty()) {
            return name.toLowerCase()
                    .replace("_", ".")
                    .replace("-", ".");
        }
        return subPackage;
    }

    /**
     * Sets the module subpackage name.
     *
     * @param subPackage subpackage
     */
    public void setSubPackage(String subPackage) {
        this.subPackage = subPackage;
    }

    /**
     * Returns the class-name prefix.
     *
     * @return prefix
     */
    public String getPrefix() {
        if(isNull(prefix) || prefix.isEmpty()){
            return getModuleName();
        }
        return NameUtil.capitalizedName(prefix);
    }

    /**
     * Sets the class-name prefix.
     *
     * @param prefix prefix value
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns the root project POM model.
     *
     * @return project POM
     */
    public Model getProjectPom() {
        return projectPom;
    }

    /**
     * Returns the backend POM model.
     *
     * @return backend POM
     */
    public Model getBackendPom() {
        return backendPom;
    }

    /**
     * Returns the frontend POM model.
     *
     * @return frontend POM
     */
    public Model getFrontendPom() {
        return frontendPom;
    }

    /**
     * Returns the compiler identifier.
     *
     * @return compiler name
     */
    public String getCompiler() {
        return compiler;
    }

    /**
     * Sets the compiler identifier.
     *
     * @param compiler compiler name
     */
    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    /**
     * Initializes the module by loading project and module POMs.
     *
     * @return this module
     */
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

    /**
     * Builds the template context map for this module.
     *
     * @return context map
     */
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
        context.put("compiler", compiler);
        context.put("modulePackage", getModulePackage());

        return context;
    }

    /**
     * Returns a formatted representation of the module state.
     *
     * @return module details
     */
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
                "\n\t compiler='" + compiler + '\'' +
                "\n\t modulePackage=" + getModulePackage() +
                '}';
    }
}
