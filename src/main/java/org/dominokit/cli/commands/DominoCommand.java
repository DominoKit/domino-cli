package org.dominokit.cli.commands;

import io.quarkus.picocli.runtime.annotations.TopCommand;
import picocli.CommandLine;

import static picocli.CommandLine.Command;

@TopCommand
@Command(
        name = "domino",
        description = "Executes domino commands",
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
