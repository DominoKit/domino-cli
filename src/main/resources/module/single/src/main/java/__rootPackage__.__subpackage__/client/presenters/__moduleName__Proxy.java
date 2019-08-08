#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set( $token = ${moduleName.toLowerCase()} )
package ${rootPackage}.${subpackage}.client.presenters;

import org.dominokit.domino.api.client.annotations.presenter.*;
import org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter;
import ${rootPackage}.${subpackage}.client.views.${moduleName}View;
import ${rootPackage}.${subpackage}.shared.events.${moduleName}Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.dominokit.domino.api.client.mvp.presenter.ViewBaseClientPresenter.DOCUMENT_BODY;

@PresenterProxy
@AutoRoute(token = "${token}")
@Slot(DOCUMENT_BODY)
@AutoReveal
@OnStateChanged(${moduleName}Event.class)
public class ${moduleName}Proxy extends ViewBaseClientPresenter<${moduleName}View> {

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