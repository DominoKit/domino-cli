package ${rootPackage}.${modulePackage};

import dagger.Component;
import javax.inject.Singleton;
import org.dominokit.brix.Brix;
import org.dominokit.brix.ModuleComponent;

@Singleton
@Component(modules = {Brix${moduleName}BindingModule_.class, Brix${moduleName}Module_.class, Brix${moduleName}UIBindingModule_.class})
public interface ${moduleName}Component extends ModuleComponent {
  static ModuleComponent getInstance() {
    return Dagger${moduleName}Component.builder()
        .brix${moduleName}Module_(Brix${moduleName}Module__Factory.newInstance(Brix.get().getCoreComponent()))
        .build();
  }
}
