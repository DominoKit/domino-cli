package org.dominokit.cli.commands;

import org.dominokit.cli.creator.module.Module;
import org.dominokit.cli.creator.module.ModuleCreatorFactory;

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
            description = "if true will generate a domino-mvp backend module, default implementation is vertx."
    )
    private boolean backend = false;


    @Override
    public void run() {

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

            ModuleCreatorFactory.get(compiler, single).create(module.init());
            System.out.println("The following module have been created");
            System.out.println(module);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
