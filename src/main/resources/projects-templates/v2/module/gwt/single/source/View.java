package ${rootPackage}.${subpackage}.client.views;

import org.dominokit.domino.api.client.mvp.view.ContentView;
import org.dominokit.domino.api.client.mvp.view.HasUiHandlers;
import org.dominokit.domino.api.client.mvp.view.UiHandlers;

public interface ${prefix}View extends ContentView, HasUiHandlers<${prefix}View.${prefix}UiHandlers> {

    void welcomeMessage(String message);

    interface ${prefix}UiHandlers extends UiHandlers {
    }
}