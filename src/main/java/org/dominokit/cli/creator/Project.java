package org.dominokit.cli.creator;

import java.util.HashMap;
import java.util.Map;

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

    public Project(String name) {
        super("", name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRootPackage() {
        return rootPackage;
    }

    public void setRootPackage(String rootPackage) {
        this.rootPackage = rootPackage;
    }

    public String getModuleShortName() {
        return moduleShortName;
    }

    public void setModuleShortName(String moduleShortName) {
        this.moduleShortName = moduleShortName;
    }

    public boolean isHasParent() {
        return hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public String getParentArtifactId() {
        return parentArtifactId;
    }

    public void setParentArtifactId(String parentArtifactId) {
        this.parentArtifactId = parentArtifactId;
    }

    public boolean isGenerateApi() {
        return generateApi;
    }

    public void setGenerateApi(boolean generateApi) {
        this.generateApi = generateApi;
    }

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
        context.put("port", generateApi ? "9090" : "8080");

        return context;
    }

    @Override
    public String toString() {
        return "Project {" +
                "\n\t name='" + name + '\'' +
                "\n\t groupId='" + groupId + '\'' +
                "\n\t artifactId='" + artifactId + '\'' +
                "\n\t version='" + version + '\'' +
                "\n\t rootPackage='" + rootPackage + '\'' +
                "\n\t moduleShortName='" + moduleShortName + '\'' +
                "\n\t hasParent=" + hasParent +
                "\n\t parentArtifactId='" + parentArtifactId + '\'' +
                "\n\t generateApi=" + generateApi +
                "\n}";
    }
}
