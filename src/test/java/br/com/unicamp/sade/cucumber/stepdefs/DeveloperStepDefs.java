package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.developer.DeveloperInformationPage;
import br.com.unicamp.sade.cucumber.selenium.pages.developer.DeveloperSignUpPage;
import br.com.unicamp.sade.cucumber.selenium.pages.developer.EditDeveloperPage;
import br.com.unicamp.sade.cucumber.selenium.pages.home.HomePage;
import br.com.unicamp.sade.cucumber.selenium.pages.home.MenuPage;
import br.com.unicamp.sade.fixtures.DeveloperFixture;
import br.com.unicamp.sade.service.dto.DeveloperDTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertEquals;
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

    @When("^i update my information")
    public void iUpdateMyInformation() {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        EditDeveloperPage editDeveloperPage = menuPage.goToDeveloperInformation().edit();
        editDeveloperPage.setPhoneNumber("19992111628");
        editDeveloperPage.setStreet("Dummy street");
        editDeveloperPage.setNumber("100");
        editDeveloperPage.submit();
    }

    @Then("^it must lead me to updated information")
    public void itMustLeadMeToUpdatedInformation() {
        DeveloperInformationPage developerInformationPage = new DeveloperInformationPage(getCurrentDriver());

        assertEquals("19992111628", developerInformationPage.phoneNumber());
        assertEquals("Dummy street", developerInformationPage.street());
        assertEquals("100", developerInformationPage.number());
    }
}
