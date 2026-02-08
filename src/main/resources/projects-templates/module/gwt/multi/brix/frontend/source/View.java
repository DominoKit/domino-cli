package ${rootPackage}.${modulePackage}.views.${subpackage};

import ${rootPackage}.${modulePackage}.presenters.${subpackage}.${prefix}PresenterUiHandlers;
import org.dominokit.brix.api.UiHandlers;
import org.dominokit.brix.api.Viewable;

public interface ${prefix}View extends Viewable {

  interface ${prefix}UiHandlers extends ${prefix}PresenterUiHandlers {
  }
}
