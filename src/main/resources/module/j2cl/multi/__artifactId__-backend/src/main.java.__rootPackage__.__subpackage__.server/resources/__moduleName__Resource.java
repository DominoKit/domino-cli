#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.server.resources;

import ${rootPackage}.${subpackage}.shared.model.${moduleName}Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/service")
public class ${moduleName}Resource {

    @Path("${moduleName}Request")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ${moduleName}Response sayHello() {
        return new ${moduleName}Response("Hello from server");
    }
}