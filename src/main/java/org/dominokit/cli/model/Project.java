package org.dominokit.cli.model;

import org.apache.velocity.VelocityContext;
import org.dominokit.jackson.annotation.JSONMapper;

@JSONMapper
public class Project implements IsContext {

    private String name;
    private String groupId;
    private String artifactId;
    private String version;
    private String rootPackage;
    private String moduleShortName;
    private boolean hasParent = false;
    private String parentArtifactId;

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

    @Override
    public VelocityContext asContext() {
        VelocityContext context = new VelocityContext();
        context.put("name", name);
        context.put("artifactId", artifactId);
        context.put("groupId", groupId);
        context.put("version", version);
        context.put("rootPackage", rootPackage);
        context.put("moduleShortName", moduleShortName);
        context.put("hasParent", hasParent);
        context.put("parentArtifactId", parentArtifactId);
        return context;
    }
}
