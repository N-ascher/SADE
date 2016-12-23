package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.error.AccessDeniedPage;
import br.com.unicamp.sade.cucumber.selenium.pages.account.LoginPage;
import br.com.unicamp.sade.cucumber.selenium.pages.home.MenuPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static br.com.unicamp.sade.cucumber.selenium.SeleniumTestCase.baseUrl;
import static org.junit.Assert.assertTrue;

public class SecurityStepDefs {

    @Given("^i am a malicious user")
    public void iAmAMaliciousUser() {
    }

    @When("^sign up with a malicious username \"([^\"]*)\"$")
    public void signUpWithAMaliciousUsername(String password) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        LoginPage loginPage = menuPage.goToLogin();
        loginPage.setUsername("admin");
        loginPage.setPassword(password);
        loginPage.submit();
        loginPage.waitForError();
    }

    @Then("^the username should not be accepted")
    public void theNewInformationShowsUpAtMyProfile() {
        LoginPage loginPage = new LoginPage(getCurrentDriver());

        assertTrue(loginPage.isError());
    }

    @When("^i navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String endpoint) {
        getCurrentDriver().get(baseUrl() + endpoint);
    }

    @Then("^an access denied error should be shown")
    public void anAccessDeniedErrorShouldBeShown() {
        AccessDeniedPage accessDeniedPage = new AccessDeniedPage(getCurrentDriver());
        assertTrue(accessDeniedPage.isAccessDenied());
    }

}
