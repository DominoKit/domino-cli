package org.dominokit.cli.commands;

import picocli.CommandLine;

import static picocli.CommandLine.Command;

/**
 * Picocli command that groups project and module generators.
 */
@Command(
        name = "generate",
        aliases = "gen",
        description = "Parent command for project and module scaffolding.",
        subcommands = {
                CommandLine.HelpCommand.class,
                GenerateAppCommand.class,
                GenerateBrixAppCommand.class,
                GenerateBasicAppCommand.class,
                GenerateModuleCommand.class
        }
)
public class GenerateCommand implements Runnable {
    /**
     * Picocli execution hook (no-op).
     */
    @Override
    public void run() {}
}
