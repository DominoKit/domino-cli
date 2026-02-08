package org.dominokit.cli.generator.module.gwt;

import org.dominokit.cli.generator.module.ModuleCreator;

/**
 * Factory for GWT module generators.
 */
public class GWTModuleFactory {

    /**
     * Returns a module creator for the given framework type.
     *
     * @param type framework type
     * @return module creator
     */
    public static ModuleCreator get(String type) {
        if ("brix".equals(type)) {
            return new BrixMultiModule();
        }
        throw new IllegalArgumentException("Invalid module framework : [" + type + "]");
    }
}
