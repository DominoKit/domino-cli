package ${rootPackage};

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ConfigUtils;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FileSystemAccess;
import io.vertx.ext.web.handler.StaticHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.nio.file.Paths;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class IndexPageFilter {
  @Inject
  Router router;

  @ConfigProperty(name = "brix.dev.web.root")
  String brixDevWebRoot;
  @ConfigProperty(name = "brix.application.root")
  String brixAppRoot;

  void init(@Observes StartupEvent ev) {

    router.route("/"+brixAppRoot+"/*")
        .handler(routingContext -> routingContext.reroute("/"));

    StaticHandler webRootStaticHandler =
        StaticHandler.create(FileSystemAccess.ROOT, systemWebRoot());

    if (ConfigUtils.getProfiles().contains("dev")) {
      router
          .route("/*")
          .handler(webRootStaticHandler);
    }
  }

  private String systemWebRoot() {
    return Paths.get(brixDevWebRoot).toAbsolutePath().toString();
  }
}
