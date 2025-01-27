package ${rootPackage}.${modulePackage}.components;

import ${rootPackage}.${modulePackage}.presenters.${subpackage}.Brix${prefix}PresenterModule_;
import ${rootPackage}.${modulePackage}.presenters.${subpackage}.${prefix}Presenter;
import ${rootPackage}.${modulePackage}.presenters.${subpackage}.${prefix}PresenterProvider;
import ${rootPackage}.${modulePackage}.ui.views.${subpackage}.Brix${prefix}ViewModule_;
import dagger.Component;
import javax.inject.Singleton;
import org.dominokit.brix.Brix;
import org.dominokit.brix.CoreComponentModule;
import org.dominokit.brix.CoreComponentModule_Factory;
import org.dominokit.brix.HasPresenterProvider;
import org.dominokit.brix.annotations.BrixComponent;
import org.dominokit.brix.api.ComponentProvider;
import org.dominokit.brix.api.IsBrixComponent;

@BrixComponent(presenter = ${prefix}Presenter.class)
@Component(
    modules = {
        Brix${prefix}PresenterModule_.class,
        Brix${prefix}ViewModule_.class,
        CoreComponentModule.class
    })
@Singleton
public interface ${prefix}Component
    extends IsBrixComponent, HasPresenterProvider<${prefix}PresenterProvider> {

  ComponentProvider<${prefix}Component> PROVIDER =
      new ComponentProvider<>() {
        @Override
        protected ${prefix}Component newInstance() {
          return Dagger${prefix}Component.builder()
              .coreComponentModule(
                  CoreComponentModule_Factory.newInstance(Brix.get().getCoreComponent()))
              .build();
        }
      };
}
