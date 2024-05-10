package org.dominokit.cli.generator.module.gwt;

import org.dominokit.cli.generator.module.ModuleCreator;

public class GWTModuleFactory {

    public static ModuleCreator get(String type, boolean single) {
        if("mvp".equals(type)) {
            if (single) {
                return new GwtSingleModule();
            }
            return new GwtMultiModule();
        }else {
            return new BrixMultiModule();
        }
    }
}
