package ${rootPackage}.${modulePackage}.tasks.${subpackage};

import dominojackson.shaded.com.google.auto.service.AutoService;
import org.dominokit.brix.api.BrixStartupTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AutoService(BrixStartupTask.class)
public class ${prefix}StartupTask extends BrixStartupTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(${prefix}StartupTask.class);

  @Override
  public void run() {
    LOGGER.info("Running ${prefix} task.");
    complete();
  }

  @Override
  public int order() {
    return 0;
  }
}
