package br.com.unicamp.sade.cucumber.selenium.pages.predefinedquestion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PreDefinedQuestionDialogPage {
    private WebDriver driver;

    public PreDefinedQuestionDialogPage(WebDriver driver) {
        this.driver = driver;
    }

    public PreDefinedQuestionsManagementPage save(String question) {
        WebElement input = driver.findElement(By.name("title"));
        input.clear();
        input.sendKeys(question);

        driver.findElement(By.cssSelector(".modal-dialog button.btn-primary")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.className("modal-dialog")));
        return new PreDefinedQuestionsManagementPage(driver);
    }
}
