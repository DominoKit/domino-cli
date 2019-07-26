package org.dominokit.cli.commands;

import picocli.CommandLine;

import static picocli.CommandLine.Command;

@Command(
        name = "generate",
        aliases = "gen",
        description = "Generates a domino template project/module",
        subcommands = {
                CommandLine.HelpCommand.class,
                GenerateAppCommand.class,
                GenerateModuleCommand.class
        }
)
public class GenerateCommand implements Runnable {
    @Override
    public void run() {}
}
