package org.dominokit.cli.generator.exception;

/**
 * Thrown when a resource file or folder cannot be created.
 */
public class FailedToCreateResourceException extends RuntimeException {
    /**
     * Creates a new exception with message and cause.
     *
     * @param message error message
     * @param cause underlying cause
     */
    public FailedToCreateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
