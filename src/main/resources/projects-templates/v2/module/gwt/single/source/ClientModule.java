package ${rootPackage}.${subpackage}.client;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.api.client.ModuleConfigurator;
import org.dominokit.domino.api.client.annotations.ClientModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientModule(name="${moduleName}")
public class ${moduleName}ClientModule implements EntryPoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(${moduleName}ClientModule.class);

	public void onModuleLoad() {
		LOGGER.info("Initializing ${moduleName} frontend module ...");
		new ModuleConfigurator().configureModule(new ${moduleName}ModuleConfiguration());
	}
}
