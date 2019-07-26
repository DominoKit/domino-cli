package org.dominokit.cli;

import org.dominokit.cli.commands.DominoCommand;
import picocli.CommandLine;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DominoCli {

    private static final Logger LOGGER=Logger.getLogger(DominoCli.class.getName());

    public static void main(String[] args) {
        try {
            CommandLine commandLine = new CommandLine(new DominoCommand());
            commandLine.execute(args);
        }catch (Exception e){
            LOGGER.log(Level.SEVERE, "failed to execute command", e);
        }
    }
}
