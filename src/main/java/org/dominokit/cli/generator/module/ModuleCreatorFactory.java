package org.dominokit.cli.generator.module;

import org.dominokit.cli.generator.module.gwt.GWTModuleFactory;

/**
 * Factory for selecting the correct module generator.
 */
public class ModuleCreatorFactory {

    /**
     * Returns the module creator.
     *
     * @return module creator
     */
    public static ModuleCreator get(){
        return GWTModuleFactory.get("brix");
    }
}
