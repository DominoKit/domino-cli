package org.dominokit.cli.structure.files;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.NullLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.dominokit.cli.model.IsContext;

import java.io.StringWriter;

public class VelocityStringContentProcessor implements FileContentProcessor<String> {

    private String templateName;
    private IsContext contextAware;

    public VelocityStringContentProcessor(String templateName, IsContext project) {
        this.templateName = templateName;
        this.contextAware = project;
    }

    @Override
    public String processedContent() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS, NullLogChute.class);
        velocityEngine.init();

        Template template = velocityEngine.getTemplate(templateName);

        StringWriter writer = new StringWriter();
        template.merge(contextAware.asContext(), writer);

        return writer.toString();
    }
}
