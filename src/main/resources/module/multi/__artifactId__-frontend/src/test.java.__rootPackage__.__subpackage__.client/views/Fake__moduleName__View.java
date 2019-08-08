#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.client.views;

import org.dominokit.domino.api.client.annotations.UiView;
import org.dominokit.domino.test.api.client.FakeElement;
import org.dominokit.domino.test.api.client.FakeView;
import ${rootPackage}.${subpackage}.client.presenters.${moduleName}Proxy_Presenter;

@UiView(presentable= ${moduleName}Proxy_Presenter.class)
public class Fake${moduleName}View extends FakeView implements ${moduleName}View {

    private FakeElement root;

    @Override
    protected void init(FakeElement root) {
        this.root = root;
    }
}
