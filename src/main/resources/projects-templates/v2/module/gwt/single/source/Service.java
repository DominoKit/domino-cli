package ${rootPackage}.${subpackage}.shared.services;

import org.dominokit.rest.shared.request.service.annotations.RequestFactory;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RequestFactory
public interface ${prefix}Service {
    @Path("/hello/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String greeting(@PathParam("name") String name);
}
