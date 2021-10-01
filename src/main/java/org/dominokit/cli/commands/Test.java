package org.dominokit.cli.commands;

import org.apache.commons.io.FileUtils;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {


        String out = Paths.get("", "out").toAbsolutePath().toString();
        try {
            File file = new File(Paths.get(out, "sample").toAbsolutePath().toString());

            if (file.exists()) {
                FileUtils.cleanDirectory(file);
                FileUtils.deleteDirectory(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        CommandLine commandLine = new CommandLine(new DominoCommand());
        String[] appArgs = new String[]{"gen", "app", "-n", "sample", "-g", "org.dominokit","-d", out};
        commandLine.execute(appArgs);

        String[] moduleArgs = new String[]{"gen", "module", "-n", "layout", "-sp", "layout", "-s","-d", out+"/sample"};
        commandLine.execute(moduleArgs);

        String[] module2Args = new String[]{"gen", "module", "-n", "menu", "-sp", "menu","-d", out+"/sample"};
        commandLine.execute(module2Args);

        String[] module3Args = new String[]{"gen", "module", "-n", "home", "-sp", "home.street", "-t","-d", out+"/sample"};
        commandLine.execute(module3Args);

        String[] module4Args = new String[]{"gen", "app", "-t", "basic", "-n", "j2cltest", "--c", "j2cl", "-g", "com.foo","-d", out+"/sample"};
        commandLine.execute(module4Args);

        String[] basicGwt = new String[]{"gen", "app", "-t", "basic", "-n", "gwttest", "-g", "com.foo","-d", out+"/sample"};
        commandLine.execute(basicGwt);
    }
}
