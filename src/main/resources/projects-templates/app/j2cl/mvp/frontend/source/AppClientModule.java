#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${rootPackage};

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ClientApp;

import org.dominokit.domino.gwt.client.app.DominoGWT;
import org.dominokit.domino.view.GwtView;
import org.dominokit.rest.DominoRestConfig;

import java.util.logging.Logger;

public class AppClientModule implements EntryPoint {

    private static final Logger LOGGER = Logger.getLogger(AppClientModule.class.getName());

    public void onModuleLoad() {
        DominoRestConfig.initDefaults();
        DominoGWT.init();
        GwtView.init();
        ClientApp.make().run();
        LOGGER.info("${name} Application frontend have been initialized.");
    }
}
