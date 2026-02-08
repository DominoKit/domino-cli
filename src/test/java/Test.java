import org.apache.commons.io.FileUtils;
import org.dominokit.cli.commands.DominoCommand;
import org.dominokit.cli.commands.PathUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {
        String out = Paths.get("", "out").toAbsolutePath().toString();
        try {
            File file = new File(Paths.get(out, "creator").toAbsolutePath().toString());

            if (file.exists()) {
                FileUtils.cleanDirectory(file);
                FileUtils.deleteDirectory(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommandLine commandLine = new CommandLine(new DominoCommand());
        String[] appArgs = new String[]{"gen", "app", "-t", "basic", "-n", "basic-gwt", "-g", "org.dominokit.samples", "-d", "./out/creator"};
        commandLine.execute(appArgs);

        commandLine = new CommandLine(new DominoCommand());
        appArgs = new String[]{"gen", "app", "-t", "basic",  "-n", "basic-j2cl", "-g", "org.dominokit.samples", "-c", "j2cl", "-d", "./out/creator" };
        commandLine.execute(appArgs);

        commandLine = new CommandLine(new DominoCommand());
        appArgs = new String[]{"gen", "app", "-t", "brix",  "-n", "dominodo", "-g", "org.dominokit.samples", "-c", "gwt", "-d", "./out/creator" };
        commandLine.execute(appArgs);

        PathUtils.setWorkingDir("./out/creator/dominodo");
        String[] moduleArgs = new String[]{"gen", "module", "-n", "shell", "-sp", "layout","-p", "Layout", "-d", "./out/creator/dominodo"};
        commandLine.execute(moduleArgs);
        moduleArgs = new String[]{"gen", "module", "-n", "shell", "-sp", "layout","-p", "Menu", "-d", "./out/creator/dominodo"};
        commandLine.execute(moduleArgs);

        moduleArgs = new String[]{"gen", "module", "-n", "backlog", "-sp", "issues","-p", "Issues", "-d", "./out/creator/dominodo"};
        commandLine.execute(moduleArgs);
        moduleArgs = new String[]{"gen", "module", "-n", "backlog", "-sp", "milestones","-p", "Milestones", "-d", "./out/creator/dominodo"};
        commandLine.execute(moduleArgs);
    }
}
