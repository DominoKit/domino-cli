package org.dominokit.cli.commands;

import org.dominokit.cli.ReleaseVersionsProfile;
import org.dominokit.cli.ToolVersion;
import picocli.CommandLine;

import java.util.List;

import static picocli.CommandLine.Command;

/**
 * Lists release profile versions.
 */
@Command(
        name = "list-versions",
        description = "List cached release profile versions (may prompt if newer releases are available).",
        subcommands = CommandLine.HelpCommand.class
)
public class ListVersionsCommand implements Runnable {

    @Override
    public void run() {
        List<ToolVersion> versions = new ReleaseVersionsProfile().getVersions();
        System.out.println("Release profile versions:");
        for (ToolVersion version : versions) {
            System.out.println(formatVersion(version));
        }
    }

    private String formatVersion(ToolVersion version) {
        return "- " + version.getKey() + " = " + version.getGroupId()
                + ":" + version.getArtifactId()
                + ":" + version.getVersion();
    }
}
