package org.dominokit.cli;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * String utilities for module and artifact naming.
 */
public class NameUtil {
    /**
     * Converts a dotted or dashed name to PascalCase.
     *
     * @param artifactId name to convert
     * @return PascalCase name
     */
    public static String capitalizedName(String artifactId) {
        return  Arrays.stream(artifactId.split("[-.]"))
                .map(NameUtil::capitalizeFirstLetter)
                .collect(Collectors.joining());
    }

    /**
     * Capitalizes the first character of the input string.
     *
     * @param input string to capitalize
     * @return string with the first character uppercased
     */
    public static String capitalizeFirstLetter(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}
