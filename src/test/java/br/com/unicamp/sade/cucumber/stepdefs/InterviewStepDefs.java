package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.developer.DeveloperInformationPage;
import br.com.unicamp.sade.cucumber.selenium.pages.home.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.interview.InterviewDialogPage;
import br.com.unicamp.sade.cucumber.selenium.pages.interview.InterviewPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Optional;
import java.util.Set;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InterviewStepDefs {
    @When("^i add interview information to \"([^\"]*)\" developer")
    public void iAddInterviewInformationToADeveloper(String devLogin) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        Optional<DeveloperInformationPage> developerInfoPage = menuPage.goToDevelopersManagement().editDeveloper(devLogin);
        assertTrue(developerInfoPage.isPresent());

        InterviewDialogPage interviewDialogPage = developerInfoPage.get().addInterview();
        interviewDialogPage.setHourValue(20);
        interviewDialogPage.setAnswer(1, "Reconhecido no mundo open source");
        interviewDialogPage.setAnswer(2, "Meu ponto fraco é ser perfeccionista e meu ponto forte é determinação");
        interviewDialogPage.addComment("Mostrou bom conhecimento em Java.");
        interviewDialogPage.addComment("Foi coerente.");
        interviewDialogPage.submit();
    }

    @Then("^the interview information should be available")
    public void theInterviewInformationShouldBeAvailable() {
        InterviewPage interviewPage = new InterviewPage(getCurrentDriver());

        assertEquals(20, interviewPage.hourValue());

        Set<String> answers = interviewPage.answers();
        assertTrue(answers.contains("Reconhecido no mundo open source"));
        assertTrue(answers.contains("Meu ponto fraco é ser perfeccionista e meu ponto forte é determinação"));

        Set<String> comments = interviewPage.comments();
        assertTrue(comments.contains("Mostrou bom conhecimento em Java."));
        assertTrue(comments.contains("Foi coerente."));
    }
}
