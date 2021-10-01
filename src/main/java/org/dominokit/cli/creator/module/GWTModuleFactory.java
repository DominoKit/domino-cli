package org.dominokit.cli.creator.module;

public class GWTModuleFactory {

    public static ModuleCreator get(boolean single) {
        if(single){
            return new GwtSingleModule();
        }
        return new GwtMultiModule();
    }
}
