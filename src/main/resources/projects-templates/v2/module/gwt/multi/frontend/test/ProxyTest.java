package ${rootPackage}.${subpackage}.client;

import org.dominokit.domino.api.client.annotations.ClientModule;
import org.dominokit.domino.test.api.client.DominoTestCase;
import org.dominokit.domino.test.api.client.DominoTestRunner;
import org.dominokit.domino.test.api.client.annotations.FakeView;
import org.dominokit.domino.test.api.client.annotations.PresenterSpy;
import org.dominokit.domino.test.api.client.annotations.TestConfig;
import ${rootPackage}.${subpackage}.client.presenters.${prefix}PresenterSpy;
import ${rootPackage}.${subpackage}.client.presenters.${prefix}Proxy;
import ${rootPackage}.${subpackage}.client.views.Fake${prefix}View;
import ${rootPackage}.${subpackage}.shared.services.${prefix}ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DominoTestRunner.class)
@ClientModule(name = "Test${prefix}")
@TestConfig(modules= {${moduleName}ModuleConfiguration.class})
public class ${prefix}ProxyTest extends DominoTestCase{

    @PresenterSpy(${prefix}Proxy.class)
    ${prefix}PresenterSpy presenterSpy;

    @FakeView(${prefix}Proxy.class)
    Fake${prefix}View fakeView;

    public ${prefix}ProxyTest() {
        super(new ${prefix}ProxyTest_Config());
    }

    @Before
    public void start(){
        testClient.withServer(getTestContext()).start();
    }

    @Test
    public void given${prefix}Module_whenRoutingTo${prefix}_thenShouldDisplayWelcomeMessage() {
        clientContext.forRequest(${prefix}ServiceFactory.${prefix}Service_greeting.class)
                .returnResponse("hello : ${prefix} proxy");
        clientContext.history().fireState("${token}");
        assertThat(presenterSpy.isActivated()).isTrue();
        assertThat(fakeView.getMessage()).isEqualTo("hello : ${prefix} proxy");
    }

    @Test
    public void given${prefix}Module_whenRoutingTo${prefix}AndServerFailed_thenDisplayErrorMessage() {
        clientContext.forRequest(${prefix}ServiceFactory.${prefix}Service_greeting.class)
                .thenFail();
        clientContext.history().fireState("${token}");
        assertThat(presenterSpy.isActivated()).isTrue();
        assertThat(fakeView.getMessage()).isEqualTo("Sadly no response from server.!");
    }
}