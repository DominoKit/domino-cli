package ${rootPackage}.${subpackage}.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.elements.DivElement;

import ${rootPackage}.${subpackage}.client.presenters.${prefix}Proxy;
import ${rootPackage}.${subpackage}.client.views.${prefix}View;


@UiView(presentable = ${prefix}Proxy.class)
public class ${prefix}ViewImpl extends BaseElementView<HTMLDivElement> implements ${prefix}View{

    private ${prefix}UiHandlers uiHandlers;
    private DivElement root = div();

    @Override
    public HTMLDivElement init() {
        return root.element();
    }

    @Override
    public void welcomeMessage(String message) {
        root.appendChild(h(1).textContent(message));
    }

    @Override
    public void setUiHandlers(${prefix}UiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}