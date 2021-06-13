package org.dominokit.cli.structure.files;

public interface FileContentProcessor<T> {
    T processedContent();

    class ProcessContentException extends RuntimeException{

        public ProcessContentException(String resourceName, Throwable t) {
            super("Failed to load resource ["+resourceName+"]", t);
        }
    }
}
