package org.dominokit.cli.commands;

import picocli.CommandLine;

import static picocli.CommandLine.Command;

@Command(
        name = "domino",
        description = "Executes domino commands",
        subcommands = {
                CommandLine.HelpCommand.class,
                GenerateCommand.class
        }
)
public class DominoCommand implements Runnable {
    @Override
    public void run() {
    }
}
