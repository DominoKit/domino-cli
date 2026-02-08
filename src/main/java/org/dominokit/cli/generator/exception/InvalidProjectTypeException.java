package org.dominokit.cli.generator.exception;

/**
 * Thrown when an unsupported project type is requested.
 */
public class InvalidProjectTypeException extends RuntimeException {
    /**
     * Creates a new exception with the provided message.
     *
     * @param message error message
     */
    public InvalidProjectTypeException(String message) {
        super(message);
    }
}
