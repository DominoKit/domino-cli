package ${rootPackage};

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.brix.Brix;
import org.dominokit.brix.api.BrixComponentInitializer;
import org.dominokit.brix.api.BrixComponentInitializer_ServiceLoader;
import org.dominokit.domino.ui.style.DominoCss;
import org.dominokit.domino.ui.utils.ElementsFactory;
import org.dominokit.domino.ui.themes.DominoThemeAccent;
import org.dominokit.domino.ui.themes.DominoThemeDefault;
import org.dominokit.domino.ui.themes.DominoThemeLight;
import org.dominokit.domino.ui.themes.DominoThemeManager;
import org.dominokit.rest.DominoRestConfig;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint, ElementsFactory, DominoCss {
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    BrixComponentInitializer_ServiceLoader.load()
        .forEach(BrixComponentInitializer::init);

    DominoRestConfig.initDefaults()
        .setDefaultServiceRoot("http://localhost:8080/api/");

    DominoThemeManager.INSTANCE.apply(DominoThemeDefault.INSTANCE);
    DominoThemeManager.INSTANCE.apply(DominoThemeLight.INSTANCE);
    DominoThemeManager.INSTANCE.apply(DominoThemeAccent.TEAL);

    ConfigServiceFactory.INSTANCE.configs()
        .onSuccess(response -> {
          Brix.get().init(response);
          Brix.get().start(() -> {
            Brix.get().config().get("brix.application.root")
                    .ifPresent(appRoot -> {
                      Brix.get().router().setRootPath(appRoot);
                    });

            Brix.get().config().get("brix.api.url")
                .ifPresent(apiUrl -> {
                  DominoRestConfig.initDefaults()
                      .setDefaultServiceRoot(apiUrl);
                });

            Brix.get().router().fireCurrentStateHistory();
          });
        })
        .onFailed(failedResponse -> {
        })
        .send();

  }
}
