package org.dominokit.cli.commands;

import org.dominokit.cli.ReleaseVersionsProfile;
import org.dominokit.cli.ToolVersion;
import org.dominokit.cli.VersionUpdateService;
import picocli.CommandLine;

import java.util.List;

import static picocli.CommandLine.Command;

/**
 * Updates cached release profile versions.
 */
@Command(
        name = "update-versions",
        description = "Check for newer release versions and update the cache on confirmation.",
        subcommands = CommandLine.HelpCommand.class
)
public class UpdateVersionsCommand implements Runnable {

    @Override
    public void run() {
        List<ToolVersion> versions = new VersionUpdateService()
                .applyReleaseVersions(ReleaseVersionsProfile.defaultVersions(), true);
        System.out.println("Cached release versions:");
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
