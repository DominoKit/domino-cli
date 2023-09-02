package ${rootPackage}.${subpackage}.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.extension.PredefinedSlots;
import ${rootPackage}.${subpackage}.client.views.${prefix}View;
import ${rootPackage}.${subpackage}.shared.events.${prefix}Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy(name = "${prefix}")
@AutoRoute(token = "${token}")
@Slot(PredefinedSlots.BODY_SLOT)
@AutoReveal
public class ${prefix}Proxy extends ViewBaseClientPresenter<${prefix}View> implements ${prefix}View.${prefix}UiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(${prefix}Proxy.class);

    @OnInit
    public void on${prefix}Init(){
        LOGGER.info("${prefix} initialized");
    }

    @OnReveal
    public void on${prefix}Revealed() {
        LOGGER.info("${prefix} view revealed");
    }
}