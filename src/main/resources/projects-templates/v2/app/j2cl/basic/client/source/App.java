package ${rootPackage};

import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLHeadingElement;
import jsinterop.annotations.JsType;
import jsinterop.base.Js;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@JsType
public class App {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        HTMLDivElement div= Js.cast(DomGlobal.document.createElement("div"));
        HTMLHeadingElement h1 = Js.cast(DomGlobal.document.createElement("h1"));
        h1.textContent ="Hello from j2cl";
        div.appendChild(h1);
        DomGlobal.document.body.appendChild(div);
    }
}
