package ${rootPackage}.${modulePackage}.presenters.${subpackage};

import org.dominokit.brix.annotations.BrixPresenter;
import org.dominokit.brix.annotations.BrixRoute;
import org.dominokit.brix.annotations.BrixSlot;
import org.dominokit.brix.annotations.OnActivated;
import org.dominokit.brix.api.BrixSlots;
import org.dominokit.brix.api.Presenter;
import ${rootPackage}.${modulePackage}.views.${subpackage}.${prefix}View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@BrixPresenter
@BrixRoute("${token}")
@BrixSlot(BrixSlots.BRIX_BODY_SLOT)
public abstract class ${prefix}Presenter extends Presenter<${prefix}View> implements ${prefix}View.${prefix}UiHandlers {

  private static final Logger LOGGER = LoggerFactory.getLogger(${prefix}Presenter.class);

  @OnActivated
  public void on${prefix}Activated(){
    LOGGER.info("${prefix} presenter activated.");
  }
}
