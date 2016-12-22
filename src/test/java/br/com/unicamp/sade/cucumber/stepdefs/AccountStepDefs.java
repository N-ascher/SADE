package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.LoginPage;
import br.com.unicamp.sade.cucumber.selenium.pages.MenuPage;
import cucumber.api.java.en.Given;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;

public class AccountStepDefs {
    @Given("^the username is \"([^\"]*)\" and the password is \"([^\"]*)\"$")
    public void theUserIsAndPasswordIs(String user, String password) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        LoginPage loginPage = menuPage.gotToLogin();
        loginPage.login(user, password);
    }
}
