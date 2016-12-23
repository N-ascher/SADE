package br.com.unicamp.sade.cucumber.selenium.pages.predefinedquestion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PreDefinedQuestionsManagementPage {
    private WebDriver driver;

    public PreDefinedQuestionsManagementPage(WebDriver driver) {
        this.driver = driver;
    }


    public PreDefinedQuestionDialogPage openCreateDialog() {
        driver.findElement(By.id("add-pre-defined-question")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.className("modal-dialog")));

        return new PreDefinedQuestionDialogPage(driver);
    }

    public Optional<String> getQuestion(String question) {
        return findQuestionRow(question)
                .map(row -> row.findElement(By.cssSelector("td:nth-child(2)")).getText()).findFirst();
    }

    private Stream<WebElement> findQuestionRow(String question) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#pre-defined-questions table tbody tr"));
        return rows.stream()
                .filter(row -> row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals(question));
    }

    public Optional<PreDefinedQuestionDialogPage> openUpdateDialog(String question) {
        return findQuestionRow(question)
                .map(row -> {
                    row.findElement(By.cssSelector("td:last-child button.btn-primary")).click();
                    new WebDriverWait(driver, 10)
                            .until(ExpectedConditions
                                    .elementToBeClickable(By.className("modal-dialog")));
                    return new PreDefinedQuestionDialogPage(driver);
                }).findFirst();
    }

    public void delete(String question) {
        Set<WebElement> rows = findQuestionRow(question).collect(Collectors.toSet());
        for (WebElement row: rows) {
            row.findElement(By.cssSelector("td:last-child button.btn-danger")).click();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.className("modal-dialog")));
            driver.findElement(By.cssSelector(".modal-dialog button.btn-danger")).click();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions
                            .elementToBeClickable(By.className("alert-success")));
        }
    }
}
