#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.ui.utils.DominoElement;

import ${rootPackage}.${subpackage}.client.presenters.${moduleName}Proxy;
import ${rootPackage}.${subpackage}.client.views.${moduleName}View;

import static org.jboss.elemento.Elements.h;

@UiView(presentable = ${moduleName}Proxy.class)
public class ${moduleName}ViewImpl extends BaseElementView<HTMLDivElement> implements ${moduleName}View{

    private ${moduleName}UiHandlers uiHandlers;

    @Override
    public HTMLDivElement init() {
        return DominoElement.div()
                .appendChild(h(1)
                    .textContent("Hello World! from module : ${moduleName}")
                    .element())
                .element();
    }

    @Override
    public void setUiHandlers(${moduleName}UiHandlers uiHandlers) {
        this.uiHandlers = uiHandlers;
    }
}