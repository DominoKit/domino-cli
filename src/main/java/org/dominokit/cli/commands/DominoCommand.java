package org.dominokit.cli.commands;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

import static picocli.CommandLine.Command;

/**
 * Top-level CLI command that routes to generator subcommands.
 */
@TopCommand
@Command(
        name = "domino",
        description = """
        DominoKit CLI entrypoint for scaffolding projects and modules,
        plus listing or updating cached dependency versions.
        """,
        subcommands = {
                CommandLine.HelpCommand.class,
                GenerateCommand.class,
                ListVersionsCommand.class,
                UpdateVersionsCommand.class
        },
        mixinStandardHelpOptions = true
)
public class DominoCommand implements Runnable {
    /**
     * Picocli execution hook (no-op).
     */
    @Override
    public void run() {
    }
}
