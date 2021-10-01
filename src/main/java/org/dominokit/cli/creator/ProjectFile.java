package org.dominokit.cli.creator;

import java.util.Map;
import java.util.function.Supplier;

public interface ProjectFile {

    String getName();
    void write(String path, Map<String, Object> context);
    default Supplier<Boolean> condition() { return () -> true;}

}
