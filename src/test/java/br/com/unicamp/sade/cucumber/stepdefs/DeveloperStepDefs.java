package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.DeveloperSignUpPage;
import br.com.unicamp.sade.cucumber.selenium.pages.HomePage;
import br.com.unicamp.sade.fixtures.DeveloperFixture;
import br.com.unicamp.sade.service.dto.DeveloperDTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertTrue;

public class DeveloperStepDefs {
    @Given("^I am a developer on register page")
    public void iAmADeveloperOnRegisterPage() {
        new HomePage(getCurrentDriver()).openDevSignUpPage();
    }

    @When("^I fill all the necessary information")
    public void iFillAllTheNecessaryInformation() {
        DeveloperSignUpPage signUpPage = new DeveloperSignUpPage(getCurrentDriver());

        DeveloperDTO newDeveloper = DeveloperFixture.developer();
        signUpPage.register(newDeveloper);
    }

    @Then("^my registration should be complete")
    public void myRegistrationShouldBeComplete() {
        DeveloperSignUpPage signUpPage = new DeveloperSignUpPage(getCurrentDriver());
        assertTrue(signUpPage.isSuccess());
    }
}
