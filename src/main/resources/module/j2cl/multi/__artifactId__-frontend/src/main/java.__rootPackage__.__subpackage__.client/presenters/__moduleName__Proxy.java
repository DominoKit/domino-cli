#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set( $token = ${moduleName.toLowerCase()} )
package ${rootPackage}.${subpackage}.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import org.dominokit.domino.api.shared.extension.PredefinedSlots;
import ${rootPackage}.${subpackage}.client.views.${moduleName}View;
import ${rootPackage}.${subpackage}.shared.events.${moduleName}Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PresenterProxy
@AutoRoute(token = "${token}")
@Slot(PredefinedSlots.BODY_SLOT)
@AutoReveal
@OnStateChanged(${moduleName}Event.class)
public class ${moduleName}Proxy extends ViewBaseClientPresenter<${moduleName}View> implements ${moduleName}View.${moduleName}UiHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(${moduleName}Proxy.class);

    @OnInit
    public void on${moduleName}Init(){
        LOGGER.info("${moduleName} initialized");
    }

    @OnReveal
    public void on${moduleName}Revealed() {
        LOGGER.info("${moduleName} view revealed");
    }
}