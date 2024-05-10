package ${rootPackage}.${modulePackage}.ui.views.${subpackage};

import static org.dominokit.domino.ui.utils.ElementsFactory.elements;

import elemental2.dom.HTMLDivElement;
import javax.inject.Inject;
import org.dominokit.brix.annotations.UiView;
import org.dominokit.brix.impl.BrixView;
import org.dominokit.domino.ui.elements.DivElement;
import ${rootPackage}.${modulePackage}.views.${subpackage}.${prefix}View;

@UiView
public class ${prefix}ViewImpl extends BrixView<HTMLDivElement, ${prefix}View.${prefix}UiHandlers> implements ${prefix}View {

  private DivElement root;

  @Inject
  public ${prefix}ViewImpl() {
    this.root = elements.div();
    init(this);
  }

  @Override
  public HTMLDivElement element() {
    return root.element();
  }
}
