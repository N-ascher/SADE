package br.com.unicamp.sade.cucumber;

import br.com.unicamp.sade.SadeApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SadeApp.class)
@SeleniumTest(driver = ChromeDriver.class, baseUrl = "http://localhost:9000")
@WebIntegrationTest(value = "server.port=9000")
public class HomeControllerTest {

    @Autowired
    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        System.out.println("hello");
    }

    @Test
    public void containsActuatorLinks() {
        System.out.println("Hello");
    }

}