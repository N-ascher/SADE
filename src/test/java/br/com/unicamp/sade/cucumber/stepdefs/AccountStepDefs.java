package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.account.ForgotPasswordPage;
import br.com.unicamp.sade.cucumber.selenium.pages.account.LoginPage;
import br.com.unicamp.sade.cucumber.selenium.pages.home.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.account.ProfileSettingsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        profileSettingsPage.setEmail("charmander@silvasauro.com");

        profileSettingsPage.submit();
    }

    @Then("^the new information shows up at my profile")
    public void theNewInformationShowsUpAtMyProfile() {
        ProfileSettingsPage profileSettingsPage = new ProfileSettingsPage(getCurrentDriver());

        assertEquals("Charmander", profileSettingsPage.firstName());
        assertEquals("da Silva Sauro", profileSettingsPage.lastName());
        assertEquals("charmander@silvasauro.com", profileSettingsPage.email());
    }

    @Given("^i am in the forgot password page")
    public void iAmInTheForgotPassword() {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        LoginPage loginPage = menuPage.goToLogin();
        loginPage.forgotPassword();
    }

    @When("^i request a password reset for the email \"([^\"]*)\"")
    public void iRequestAPasswordResetForTheEmail(String email) {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(getCurrentDriver());
        forgotPasswordPage.setEmail(email);
        forgotPasswordPage.submit();
    }

    @Then("^an email with instructions should be sent to me")
    public void anEmailWithInstructionsShouldBeSentToMe() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(getCurrentDriver());

        //TODO add tests integrated with email
        assertTrue(forgotPasswordPage.isSuccess());
    }
}
