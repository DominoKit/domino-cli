package org.dominokit.cli.generator.exception;

public class FailedToCreateResourceException extends RuntimeException {
    public FailedToCreateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
