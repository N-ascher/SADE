package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.LoginPage;
import br.com.unicamp.sade.cucumber.selenium.pages.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.ProfileSettingsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertEquals;

public class AccountStepDefs {
    @Given("^the username is \"([^\"]*)\" and the password is \"([^\"]*)\"$")
    public void theUserIsAndPasswordIs(String user, String password) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        LoginPage loginPage = menuPage.goToLogin();
        loginPage.login(user, password);
    }

    @When("^i edit my profile settings")
    public void iEditMyProfileConfiguration() {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        ProfileSettingsPage profileSettingsPage = menuPage.goToProfileSettings();

        profileSettingsPage.setFirstName("Charmander");
        profileSettingsPage.setLastName("da Silva Sauro");
        profileSettingsPage.setEmail("charmander@sauro.com");

        profileSettingsPage.submit();
    }

    @Then("^the new information shows up at my profile")
    public void theNewInformationShowsUpAtMyProfile() {
        ProfileSettingsPage profileSettingsPage = new ProfileSettingsPage(getCurrentDriver());

        assertEquals("Charmander", profileSettingsPage.firstName());
        assertEquals("da Silva Sauro", profileSettingsPage.lastName());
        assertEquals("charmander@sauro.com", profileSettingsPage.email());
    }
}
