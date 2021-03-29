#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.shared.model;

import org.dominokit.jackson.annotation.JSONMapper;

@JSONMapper
public class ${moduleName}Response{

    private String serverMessage;

    public ${moduleName}Response() {
    }

    public ${moduleName}Response(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }
}
