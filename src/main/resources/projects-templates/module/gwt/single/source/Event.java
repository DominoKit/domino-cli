package ${rootPackage}.${subpackage}.shared.events;

import org.dominokit.domino.api.shared.extension.ActivationEvent;

public class ${prefix}Event extends ActivationEvent {
    public ${prefix}Event(boolean status) {
        super(status);
    }

    public ${prefix}Event(String serializedEvent) {
        super(serializedEvent);
    }
}
