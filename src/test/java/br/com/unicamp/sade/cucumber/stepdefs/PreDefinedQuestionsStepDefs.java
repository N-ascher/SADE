package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.home.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.predefinedquestion.PreDefinedQuestionDialogPage;
import br.com.unicamp.sade.cucumber.selenium.pages.predefinedquestion.PreDefinedQuestionsManagementPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Optional;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PreDefinedQuestionsStepDefs {
    @When("^i add a new pre defined question \"([^\"]*)\"$")
    public void iAddANewInterviewPreDefinedQuestion(String question) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        PreDefinedQuestionsManagementPage preDefinedQuestionsPage = menuPage.goToPreDefinedQuestionsManagement();
        preDefinedQuestionsPage.openCreateDialog().save(question);
    }

    @Then("^the pre defined question \"([^\"]*)\" should be available")
    public void thePreDefinedQuestionShouldBeAvailable(String question) {
        PreDefinedQuestionsManagementPage preDefinedQuestionsManagementPage =
                new PreDefinedQuestionsManagementPage(getCurrentDriver());

        Optional<String> found = preDefinedQuestionsManagementPage.getQuestion(question);
        assertTrue(found.isPresent());
    }

    @When("^i update the pre defined question \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iUpdateThePreDefinedQuestionTo(String oldQuestion, String newQuestion) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        PreDefinedQuestionsManagementPage preDefinedQuestionsPage = menuPage.goToPreDefinedQuestionsManagement();

        Optional<PreDefinedQuestionDialogPage> dialogPage = preDefinedQuestionsPage.openUpdateDialog(oldQuestion);
        assertTrue(dialogPage.isPresent());

        dialogPage.get().save(newQuestion);
    }

    @Then("^the pre defined question \"([^\"]*)\" should not be available")
    public void theInformationShouldNotBeAvailable(String question) {
        PreDefinedQuestionsManagementPage preDefinedQuestionsManagementPage =
                new PreDefinedQuestionsManagementPage(getCurrentDriver());

        Optional<String> found = preDefinedQuestionsManagementPage.getQuestion(question);
        assertFalse(found.isPresent());
    }

}
