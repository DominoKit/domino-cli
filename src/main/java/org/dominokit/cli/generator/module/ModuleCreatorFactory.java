package org.dominokit.cli.generator.module;

import org.dominokit.cli.generator.exception.InvalidCompilerTypeException;
import org.dominokit.cli.generator.module.gwt.GWTModuleFactory;
import org.dominokit.cli.generator.module.j2cl.J2CLModuleFactory;

public class ModuleCreatorFactory {

    public static ModuleCreator get(String compiler, boolean single){

        switch (compiler.toLowerCase()){
            case "gwt": return GWTModuleFactory.get(single);
            case "j2cl": return J2CLModuleFactory.get(single);
            default:throw new InvalidCompilerTypeException("Invalid compiler type : ["+compiler+"]");
        }
    }
}
