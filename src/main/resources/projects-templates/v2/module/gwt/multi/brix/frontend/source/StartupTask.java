package ${rootPackage}.${modulePackage}.tasks.${subpackage};

import org.dominokit.brix.annotations.Task;
import org.dominokit.brix.api.StartupTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Task
public class ${prefix}StartupTask extends StartupTask {

  private static final Logger LOGGER = LoggerFactory.getLogger(${prefix}StartupTask.class);

  @Override
  public void run() {
    LOGGER.info("Running ${moduleName} task.");
    complete();
  }

  @Override
  public int order() {
    return 0;
  }
}
