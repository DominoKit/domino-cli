#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.shared.events;

import org.dominokit.domino.api.shared.extension.ActivationEvent;

public class ${moduleName}Event extends ActivationEvent {
    public ${moduleName}Event(boolean status) {
        super(status);
    }

    public ${moduleName}Event(String serializedEvent) {
        super(serializedEvent);
    }
}
