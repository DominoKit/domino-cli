package org.dominokit.cli.structure.files;

import org.dominokit.cli.model.IsContext;
import org.dominokit.jackson.annotation.JSONMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@JSONMapper
public class TemplateFile implements ProjectFile{

    private String name;
    private String template;
    private TemplateType type = TemplateType.VELOCITY;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public TemplateType getType() {
        return type;
    }

    public void setType(TemplateType type) {
        this.type = type;
    }

    @Override
    public void write(Path path, IsContext context) throws IOException{

        Path targetPath = Paths.get(path.toAbsolutePath().toString(), name);
        if(TemplateType.RESOURCE.equals(type)){
            write(targetPath, new ResourceFileContentProcessor(template).processedContent());
        }else{
            write(targetPath, new VelocityContentProcessor(template, context).processedContent());
        }
    }

    private void write(Path targetPath, String content) throws IOException{
        Files.write(targetPath, content.getBytes());
    }
}
