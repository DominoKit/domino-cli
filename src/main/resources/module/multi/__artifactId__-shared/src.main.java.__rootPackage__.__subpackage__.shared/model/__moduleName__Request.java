#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.shared.model;

import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class ${moduleName}Request {

    private String message;

    public ${moduleName}Request() {
    }

    public ${moduleName}Request(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
