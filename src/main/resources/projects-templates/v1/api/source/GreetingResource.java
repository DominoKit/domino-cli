package ${rootPackage};

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Path("/hello/{name}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String greeting(@PathParam("name") String name) {
        return "hello : "+name;
    }
}