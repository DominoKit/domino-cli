package org.dominokit.cli;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NameUtil {
    public static String capitalizedName(String artifactId) {
        return  Arrays.stream(artifactId.split("[-.]"))
                .map(NameUtil::capitalizeFirstLetter)
                .collect(Collectors.joining());
    }

    public static String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
