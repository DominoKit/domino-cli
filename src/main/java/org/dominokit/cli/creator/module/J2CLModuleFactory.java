package org.dominokit.cli.creator.module;

public class J2CLModuleFactory {

    public static ModuleCreator get(boolean single) {
        if(single){
            return new GwtSingleModule();
        }
        return new GwtMultiModule();
    }
}
