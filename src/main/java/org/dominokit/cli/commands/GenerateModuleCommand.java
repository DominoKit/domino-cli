package org.dominokit.cli.commands;

import org.dominokit.cli.PomUtil;
import org.dominokit.cli.VersionProfile;
import org.dominokit.cli.generator.module.Module;
import org.dominokit.cli.generator.module.ModuleCreatorFactory;

import static java.util.Objects.isNull;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.HelpCommand;
import static picocli.CommandLine.Option;

@Command(
        name = "module",
        description = "Use with generate command to generate a domino-mvp module template",
        subcommands = HelpCommand.class
)
public class GenerateModuleCommand implements Runnable {

    private static final String VERSION = "1.0-SNAPSHOT";
    @Option(
            names = {"-n", "--name"},
            description = "The module name, also will be use as the artifact ID",
            required = true
    )
    private String name;

    @Option(
            names = {"-s", "--single"},
            fallbackValue = "true",
            defaultValue = "false",
            description = "If true it will merge client an shared as one module, a backend module will not be generated"
    )
    private boolean single = false;

    @Option(
            names = {"-v", "--version"},
            description = "DominoKit tools version " +
                    "\n\t\t -[v1] : Will generate a project using DominoKi tools version 1.x.x" +
                    "\n\t\t -[v2] : Will generate a project using DominoKi tools version 2.x.x" +
                    "\n\t\t -[dev] : Will generate a project using DominoKi tools HEAD-SNAPSHOT versions",
            defaultValue = "v2"
    )
    private String version;

    @Option(
            names = {"-t", "--tests"},
            fallbackValue = "true",
            defaultValue = "false",
            description = "if true will generate tests for a multi submodules module."
    )
    private boolean generateTests = false;

    @Option(
            names = {"-c", "--compiler"},
            fallbackValue = "gwt",
            defaultValue = "gwt",
            description = "The Java to JavaScript compiler to be used possible values [gwt, j2cl] default is [gwt]"
    )
    private String compiler;
    @Option(
            names = {"-f", "--framework"},
            fallbackValue = "mvp",
            defaultValue = "mvp",
        description = "The target framework " +
            "\n\t\t -[mvp] : [Default] Will generate domino-mvp module." +
            "\n\t\t -[brix] : Will generate domino-brix module"
    )
    private String framework;

    @Option(
            names = {"-sp", "--subpackage"},
            description = "the module sub package, this will be appended to the application rootPackage"
    )
    private String subPackage;

    @Option(
            names = {"-p", "--prefix"},
            description = "The module prefix to be used in the generated classes name, if not present module name will be used instead"
    )
    private String prefix;

    @Option(
            names = {"-d", "--dir"},
            description = "absolute path to the module where the project should be generated."
    )
    private String workingDire;

    @Option(
            names = {"-b", "--backend"},
            fallbackValue = "false",
            defaultValue = "false",
            description = "if true will generate a domino-mvp backend module, default implementation is vertx. - not supported by brix -"
    )
    private boolean backend = false;


    @Override
    public void run() {

        VersionProfile.setVersion(version);
        PathUtils.setWorkingDir(workingDire);

        try {

            Module module = new Module(name);

            module.setArtifactId(name);
            module.setGenerateTests(generateTests);
            module.setCompiler(compiler);
            module.setPrefix(prefix);
            module.setGenerateBackend(backend);

            if (isNull(subPackage) || subPackage.trim().isEmpty()) {
                subPackage = name.toLowerCase()
                        .replace("_", ".")
                        .replace("-", ".");
            }
            module.setSubPackage(subPackage);

            ModuleCreatorFactory.get(framework, compiler, single).create(module.init());
            System.out.println("The following module have been created");
            System.out.println(module);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
