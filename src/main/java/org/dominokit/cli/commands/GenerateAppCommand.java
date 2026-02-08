package org.dominokit.cli.commands;

import java.util.logging.Logger;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.HelpCommand;
import static picocli.CommandLine.Option;

/**
 * Generates a DominoKit application template project.
 */
@Command(
        name = "app",
        description = "Generate a DominoKit application scaffold (basic or brix).",
        subcommands = HelpCommand.class
)
public class GenerateAppCommand implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(GenerateAppCommand.class.getName());

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
            names = {"-t", "--type"},
            description = "Template type:" +
                    "\n\t\t -[basic] : client/shared/server modules with a simple setup" +
                    "\n\t\t -[brix] : domino-brix frontend/backend/shared layout and assets",
            defaultValue = "brix"
    )
    private String type;

    @Option(
            names = {"-dev", "--dev"},
            description = "Use HEAD-SNAPSHOT DominoKit versions instead of release versions."
    )
    private boolean dev;

    @Option(
            names = {"-api", "--generate-api"},
            fallbackValue = "true",
            defaultValue = "true",
            description = "Generate an API module using Quarkus REST (JAX-RS). Not supported for Brix apps."
    )
    private boolean generateApi;


    /**
     * Executes project generation based on command options.
     */
    @Override
    public void run() {
        AppGenerator.generate(name, groupId, workingDire, type, dev, generateApi, AppGenerator.DEFAULT_VERSION, LOGGER);
    }
}
