#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
#set( $token = ${moduleName.toLowerCase()} )
package ${rootPackage}.${subpackage}.client;

import org.dominokit.domino.api.client.annotations.ClientModule;
import org.dominokit.domino.test.api.client.ClientContext;
import org.dominokit.domino.test.api.client.annotations.FakeView;
import org.dominokit.domino.test.api.client.annotations.OnBeforeClientStart;
import org.dominokit.domino.test.api.client.annotations.PresenterSpy;
import org.dominokit.domino.test.api.client.annotations.TestConfig;
import org.dominokit.domino.test.api.client.DominoTestCase;
import org.dominokit.domino.test.api.client.DominoTestRunner;
import ${rootPackage}.${subpackage}.client.presenters.${moduleName}PresenterSpy;
import ${rootPackage}.${subpackage}.client.presenters.${moduleName}Proxy;
import ${rootPackage}.${subpackage}.shared.services.${moduleName}ServiceFactory;
import ${rootPackage}.${subpackage}.client.views.Fake${moduleName}View;
import ${rootPackage}.${subpackage}.shared.model.${moduleName}Request;
import ${rootPackage}.${subpackage}.shared.model.${moduleName}Response;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(DominoTestRunner.class)
@ClientModule(name = "Test${moduleName}")
@TestConfig(modules= {${moduleName}ModuleConfiguration.class})
public class ${moduleName}ClientModuleTest extends DominoTestCase{

    @PresenterSpy(${moduleName}Proxy.class)
    ${moduleName}PresenterSpy presenterSpy;

    @FakeView(${moduleName}Proxy.class)
    Fake${moduleName}View fakeView;

    public ${moduleName}ClientModuleTest() {
        super(new ${moduleName}ClientModuleTest_Config());
    }

    @OnBeforeClientStart
    public void mockRequest(ClientContext clientContext) {
        clientContext.forRequest(${moduleName}ServiceFactory.${moduleName}Service_request.class)
            .returnResponse(new ${moduleName}Response("Server message"));
    }

    @Test
    public void given${moduleName}Module_whenRoutingTo${moduleName}_thenShould${moduleName}Proxy_PresenterShouldBeActivated() {
        clientContext.history().fireState("${token}");
        assertThat(presenterSpy.isActivated()).isTrue();
    }

    @Test
    public void given${moduleName}ClientModule_when${moduleName}ServerRequestIsSent_thenServerMessageShouldBeRecieved() {
        ${moduleName}ServiceFactory.INSTANCE.request(new ${moduleName}Request("client message"))
                .onSuccess(response -> assertThat(response.getServerMessage()).isEqualTo("Server message"))
                .onFailed(failedResponse -> fail(failedResponse.getThrowable().getMessage()))
                .send();
    }
}