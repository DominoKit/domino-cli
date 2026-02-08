package org.dominokit.cli.generator.project;

import org.dominokit.cli.generator.Folder;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

/**
 * Model representing a generated project and its template context.
 */
public class Project extends Folder {

    private String name;
    private String groupId;
    private String artifactId;
    private String version;
    private String rootPackage;
    private String moduleShortName;
    private boolean hasParent;
    private String parentArtifactId;
    private boolean generateApi;
    private String compiler;

    /**
     * Creates a project folder rooted at the project name.
     *
     * @param name project name
     */
    public Project(String name) {
        super("", name);
        this.name = name;
    }

    /**
     * Returns the project name.
     *
     * @return project name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the project name.
     *
     * @param name project name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the groupId.
     *
     * @return groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets the groupId.
     *
     * @param groupId groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Returns the artifactId.
     *
     * @return artifactId
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Sets the artifactId.
     *
     * @param artifactId artifactId
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Returns the project version.
     *
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the project version.
     *
     * @param version version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Returns the root package name.
     *
     * @return root package
     */
    public String getRootPackage() {
        return rootPackage;
    }

    /**
     * Sets the root package name.
     *
     * @param rootPackage root package
     */
    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    /**
     * Returns the shortened module name.
     *
     * @return module short name
     */
    public String getModuleShortName() {
        return moduleShortName;
    }

    /**
     * Sets the shortened module name.
     *
     * @param moduleShortName module short name
     */
    public void setModuleShortName(String moduleShortName) {
        this.moduleShortName = moduleShortName;
    }

    /**
     * Returns whether the project has a parent POM.
     *
     * @return true if parent POM is present
     */
    public boolean isHasParent() {
        return hasParent;
    }

    /**
     * Sets whether the project has a parent POM.
     *
     * @param hasParent true if parent POM exists
     */
    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    /**
     * Returns the parent artifactId, if any.
     *
     * @return parent artifactId
     */
    public String getParentArtifactId() {
        return parentArtifactId;
    }

    /**
     * Sets the parent artifactId.
     *
     * @param parentArtifactId parent artifactId
     */
    public void setParentArtifactId(String parentArtifactId) {
        this.parentArtifactId = parentArtifactId;
    }

    /**
     * Returns whether to generate an API module.
     *
     * @return true if API module is enabled
     */
    public boolean isGenerateApi() {
        return generateApi;
    }

    /**
     * Sets whether to generate an API module.
     *
     * @param generateApi true to generate the API module
     */
    public void setGenerateApi(boolean generateApi) {
        this.generateApi = generateApi;
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
     * Builds the template context map for this project.
     *
     * @return context map
     */
    public Map<String, Object> context() {
        Map<String, Object> context = new HashMap<>();
        context.put("name", name);
        context.put("groupId", groupId);
        context.put("artifactId", artifactId);
        context.put("version", version);
        context.put("rootPackage", rootPackage);
        context.put("moduleShortName", moduleShortName);
        context.put("hasParent", hasParent);
        context.put("parentArtifactId", parentArtifactId);
        context.put("generateApi", generateApi);
        context.put("compiler", compiler);
        context.put("port", generateApi ? "9090" : "8080");

        return context;
    }

    /**
     * Returns a formatted representation of the project state.
     *
     * @return project details
     */
    @Override
    public String toString() {
        return "Project {" +
                "\n\t name='" + name + '\'' +
                "\n\t groupId='" + groupId + '\'' +
                "\n\t artifactId='" + artifactId + '\'' +
                "\n\t version='" + version + '\'' +
                "\n\t compiler='" + compiler + '\'' +
                "\n\t rootPackage='" + rootPackage + '\'' +
                "\n\t moduleShortName='" + moduleShortName + '\'' +
                "\n\t hasParent=" + hasParent +
                "\n\t parentArtifactId='" + emptyIfNull(parentArtifactId) + '\'' +
                "\n\t generateApi=" + generateApi +
                "\n}";
    }

    private String emptyIfNull(String value){
        return isNull(value)?"":value;
    }
}
