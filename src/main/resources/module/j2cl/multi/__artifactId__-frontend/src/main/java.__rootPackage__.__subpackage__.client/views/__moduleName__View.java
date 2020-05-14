#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;

public interface ${moduleName}View extends ContentView, HasUiHandlers<${moduleName}View.${moduleName}UiHandlers> {

    interface ${moduleName}UiHandlers extends UiHandlers {
    }
}