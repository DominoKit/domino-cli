package org.dominokit.cli.generator.module.gwt;

import org.dominokit.cli.generator.module.ModuleCreator;

public class GWTModuleFactory {

    public static ModuleCreator get(boolean single) {
        if(single){
            return new GwtSingleModule();
        }
        return new GwtMultiModule();
    }
}
