#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${rootPackage};

import com.google.gwt.core.client.EntryPoint;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import jsinterop.base.Js;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        HTMLDivElement divElement = Js.uncheckedCast(DomGlobal.document.createElement("div"));
        divElement.textContent = "Welcome from simple j2cl app..!";
        divElement.style.setProperty("width", "100%");
        divElement.style.setProperty("font-size", "32px");
        divElement.style.setProperty("text-align", "center");
        DomGlobal.document.body.appendChild(divElement);
        DomGlobal.console.info("Welcome from simple j2cl app..!");
    }
}
