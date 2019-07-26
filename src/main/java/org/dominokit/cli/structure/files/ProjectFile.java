package org.dominokit.cli.structure.files;

import org.dominokit.cli.model.IsContext;

import java.io.IOException;
import java.nio.file.Path;

public interface ProjectFile {
    void write(Path path, IsContext context) throws IOException;

    static ProjectFile create(String name, String content){
        return new ContentFile(name, content);
    }

    static ProjectFile fromResource(String name, String resourceName){
        return new ContentFile(name, new ResourceFileContentProcessor(resourceName).processedContent());
    }

    static ProjectFile fromTemplate(String name, String templateName, IsContext context){
        return new ContentFile(name, new VelocityContentProcessor(templateName, context).processedContent());
    }

    static ProjectFile copy(String name, String resourceName){
        return new CopyFile(name, resourceName);
    }


}
