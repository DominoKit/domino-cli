package org.dominokit.cli.creator.module;

import org.dominokit.cli.creator.exception.InvalidCompilerTypeException;

public class ModuleCreatorFactory {

    public static ModuleCreator get(String compiler, boolean single){

        switch (compiler.toLowerCase()){
            case "gwt": return GWTModuleFactory.get(single);
            case "j2cl": return J2CLModuleFactory.get(single);
            default:throw new InvalidCompilerTypeException("Invalid compiler type : ["+compiler+"]");
        }
    }
}
