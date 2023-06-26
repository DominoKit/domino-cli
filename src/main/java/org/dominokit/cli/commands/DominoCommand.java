package org.dominokit.cli.commands;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

import static picocli.CommandLine.Command;

@TopCommand
@Command(
        name = "domino",
        description = """
        Executes domino commands
        Use this command to generate basic template project or an MVP project.
        The versions used in the project dependencies can be set dynamically using env variable as the following :
        
        - DOMINO_CLI_DOMINO_UI_VERSION
        - DOMINO_CLI_DOMINO_HISTORY_VERSION
        - DOMINO_CLI_DOMINO_MVP_VERSION
        - DOMINO_CLI_DOMINO_REST_VERSION
        - DOMINO_CLI_DOMINO_JACKSON_VERSION
        - DOMINO_CLI_QUARKUS_VERSION
        - DOMINO_CLI_VERTX_VERSION
        - DOMINO_CLI_GWT_VERSION
        - DOMINO_CLI_J2CL_PLUGIN_VERSION
        """,
        subcommands = {
                CommandLine.HelpCommand.class,
                GenerateCommand.class
        },
        mixinStandardHelpOptions = true
)
public class DominoCommand implements Runnable {
    @Override
    public void run() {
    }
}
