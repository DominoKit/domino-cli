package org.dominokit.cli.commands;

import org.dominokit.cli.VersionProfile;
import org.dominokit.cli.generator.module.Module;
import org.dominokit.cli.generator.module.ModuleCreatorFactory;

import static java.util.Objects.isNull;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.HelpCommand;
import static picocli.CommandLine.Option;

/**
 * Generates a DominoKit Brix module template inside an existing project.
 */
@Command(
        name = "module",
        description = "Generate a DominoKit Brix module inside an existing project.",
        subcommands = HelpCommand.class
)
public class GenerateModuleCommand implements Runnable {

    @Option(
            names = {"-n", "--name"},
            description = "Module name; used as the artifactId for generated modules.",
            required = true
    )
    private String name;


    @Option(
            names = {"-sp", "--subpackage"},
            description = "Subpackage appended to the application root package."
    )
    private String subPackage;

    @Option(
            names = {"-p", "--prefix"},
            description = "Prefix for generated class names; defaults to the module name."
    )
    private String prefix;

    @Option(
            names = {"-d", "--dir"},
            description = "Absolute path where the module should be generated (defaults to the current directory)."
    )
    private String workingDire;

    /**
     * Executes module generation based on command options.
     */
    @Override
    public void run() {

        VersionProfile.setVersion("release");
        PathUtils.setWorkingDir(workingDire);

        try {

            Module module = new Module(name);

            module.setArtifactId(name);
            module.setCompiler("gwt");
            module.setPrefix(prefix);

            if (isNull(subPackage) || subPackage.trim().isEmpty()) {
                subPackage = name.toLowerCase()
                        .replace("_", ".")
                        .replace("-", ".");
            }
            module.setSubPackage(subPackage);

            ModuleCreatorFactory.get().create(module.init());
            System.out.println("The following module have been created");
            System.out.println(module);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
