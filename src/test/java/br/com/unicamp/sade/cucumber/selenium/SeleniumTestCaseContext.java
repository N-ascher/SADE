package br.com.unicamp.sade.cucumber.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@ComponentScan(basePackageClasses = SeleniumTestCaseContext.class)
public class SeleniumTestCaseContext {

    @Bean
    public WebDriver webDriver() {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File chromedriver = new File(classpathRoot, "driver/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
        return new ChromeDriver();
    }

}
