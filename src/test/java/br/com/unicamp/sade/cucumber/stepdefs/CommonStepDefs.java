package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.SeleniumTestCase;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.com.unicamp.sade.cucumber.selenium.Driver.closeCurrentDriver;
import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;

public class CommonStepDefs extends SeleniumTestCase {

    public static String baseUrl() {
        return "http://localhost:9000";
    }

    @Before
    public void setup() {
        getCurrentDriver().get(baseUrl());
        new WebDriverWait(getCurrentDriver(), 10)
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(By.tagName("h1"), "Bem vindo ao SADE!"));
    }

    @After
    public void finish() {
        closeCurrentDriver();
    }

}
