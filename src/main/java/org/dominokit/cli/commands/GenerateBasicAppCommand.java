package org.dominokit.cli.commands;

import java.util.logging.Logger;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.HelpCommand;
import static picocli.CommandLine.Option;

/**
 * Generates a DominoKit basic application scaffold.
 */
@Command(
        name = "basic-app",
        description = "Generate a DominoKit basic application scaffold.",
        subcommands = HelpCommand.class
)
public class GenerateBasicAppCommand implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(GenerateBasicAppCommand.class.getName());

    @Option(
            names = {"-n", "--name"},
            description = "Project name; used as the artifactId and module short name.",
            required = true
    )
    private String name;

    @Option(
            names = {"-g", "--groupId"},
            description = "Project groupId; also used as the root Java package."
    )
    private String groupId;

    @Option(
            names = {"-d", "--dir"},
            description = "Absolute path to the directory where the project should be generated (defaults to the current directory)."
    )
    private String workingDire;

    @Option(
            names = {"-dev", "--dev"},
            description = "Use HEAD-SNAPSHOT DominoKit versions instead of release versions."
    )
    private boolean dev;

    @Option(
            names = {"-api", "--generate-api"},
            fallbackValue = "true",
            defaultValue = "true",
            description = "Generate an API module using Quarkus REST (JAX-RS)."
    )
    private boolean generateApi;

    @Override
    public void run() {
        AppGenerator.generate(name, groupId, workingDire, "basic", dev, generateApi, AppGenerator.DEFAULT_VERSION, LOGGER);
    }
}
