package org.dominokit.cli.structure.files;

import org.apache.commons.io.IOUtils;

import java.util.logging.Logger;

public class ResourceFileContentProcessor implements FileContentProcessor<byte[]> {

    private static final Logger LOGGER = Logger.getLogger(ResourceFileContentProcessor.class.getCanonicalName());

    private String resourceName;

    public ResourceFileContentProcessor(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public byte[] processedContent() {
        try {

            byte[] bytes = IOUtils.resourceToByteArray(resourceName, getClass().getClassLoader());

            return bytes;
        } catch (Exception e) {
            throw new ProcessContentException(resourceName, e);
        }
    }
}
