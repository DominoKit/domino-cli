#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.client.views.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.view.BaseElementView;

import org.dominokit.domino.api.client.annotations.UiView;

import ${rootPackage}.${subpackage}.client.presenters.${moduleName}Proxy;
import ${rootPackage}.${subpackage}.client.views.${moduleName}View;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;

@UiView(presentable = ${moduleName}Proxy.class)
public class ${moduleName}ViewImpl extends BaseElementView<HTMLDivElement> implements ${moduleName}View{

    @Override
    public void init(HTMLDivElement root) {
        root.appendChild(h(1).textContent("Hello World! from module : ${moduleName}").asElement());
    }

    @Override
    public HTMLDivElement createRoot() {
        return div().asElement();
    }
}