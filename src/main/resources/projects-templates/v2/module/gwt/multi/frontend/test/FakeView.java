package ${rootPackage}.${subpackage}.client.views;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.test.api.client.FakeElement;
import org.dominokit.domino.test.api.client.FakeView;
import ${rootPackage}.${subpackage}.client.presenters.${prefix}Proxy_Presenter;

@UiView(presentable= ${prefix}Proxy_Presenter.class)
public class Fake${prefix}View extends FakeView implements ${prefix}View {

    private FakeElement root;
    private ${prefix}UiHandlers uiHandlers;
    private String message;

    protected void init(FakeElement root) {
        this.root = root;
    }

    public void setUiHandlers(${prefix}UiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }

    @Override
    public void welcomeMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
