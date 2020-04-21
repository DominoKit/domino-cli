#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${rootPackage}.${subpackage}.client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(${moduleName}ClientModuleTest.class)
public class ${moduleName}ClientModuleTestSuite {
}
