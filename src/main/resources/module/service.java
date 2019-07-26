#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.shared.services;

import org.dominokit.domino.rest.shared.request.service.annotations.RequestFactory;
import ${rootPackage}.${subpackage}.shared.model.${moduleName}Response;
import ${rootPackage}.${subpackage}.shared.model.${moduleName}Request;

import javax.ws.rs.Path;

@RequestFactory
public interface ${moduleName}Service {
    @Path("${moduleName}Request")
    ${moduleName}Response request(${moduleName}Request request);
}
