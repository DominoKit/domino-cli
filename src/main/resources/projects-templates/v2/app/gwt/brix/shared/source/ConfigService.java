package ${rootPackage};

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import java.util.Map;
import org.dominokit.rest.shared.request.service.annotations.RequestFactory;

@RequestFactory
@Path("config")
public interface ConfigService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  Map<String, String> configs();

}
