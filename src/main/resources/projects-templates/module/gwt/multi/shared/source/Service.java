package ${rootPackage}.${subpackage}.shared.services;

import org.dominokit.rest.shared.request.service.annotations.RequestFactory;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestFactory
public interface ${prefix}Service {
    @Path("/hello/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String greeting(@PathParam("name") String name);
}
