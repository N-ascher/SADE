package br.com.unicamp.sade.cucumber.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.com.unicamp.sade.cucumber.selenium.Driver.closeCurrentDriver;
import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static br.com.unicamp.sade.cucumber.selenium.SeleniumTestCase.baseUrl;

public class CommonStepDefs {
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
