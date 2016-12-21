package br.com.unicamp.sade.cucumber.selenium;

import br.com.unicamp.sade.SadeApp;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SeleniumTestCaseContext.class)
@SpringBootTest(classes = SadeApp.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, value = "server.port=9000")
public abstract class SeleniumTestCase {
    public static String baseUrl() {
        return "http://localhost:9000";
    }
}
