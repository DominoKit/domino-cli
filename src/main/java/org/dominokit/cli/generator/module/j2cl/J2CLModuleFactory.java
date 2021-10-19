package org.dominokit.cli.generator.module.j2cl;

import org.dominokit.cli.generator.module.ModuleCreator;
import org.dominokit.cli.generator.module.gwt.GwtMultiModule;
import org.dominokit.cli.generator.module.gwt.GwtSingleModule;

public class J2CLModuleFactory {

    public static ModuleCreator get(boolean single) {
        if(single){
            return new GwtSingleModule();
        }
        return new GwtMultiModule();
    }
}
