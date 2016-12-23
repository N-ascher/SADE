package br.com.unicamp.sade.cucumber.selenium.pages.interview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InterviewDialogPage {
    private WebDriver driver;

    public InterviewDialogPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setHourValue(Integer hourValue) {
        clickBasicInfoTab();
        WebElement input = driver.findElement(By.name("hourValue"));
        input.clear();
        input.sendKeys(Integer.toString(hourValue));
    }

    public void setAnswer(int questionId, String answer) {
        clickBasicInfoTab();
        WebElement input = driver.findElement(By.name("question" + questionId));
        input.clear();
        input.sendKeys(answer);
    }

    public void addComment(String comment) {
        clickCommentsTab();
        WebElement input = driver.findElement(By.name("comment"));
        input.clear();
        input.sendKeys(comment);

        driver.findElement(By.id("addComment")).click();
    }

    public InterviewPage submit() {
        driver.findElement(By.cssSelector(".modal-dialog button.btn-primary")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.className("modal-dialog")));

        return new InterviewPage(driver);
    }

    private void clickBasicInfoTab() {
        driver.findElement(By.id("basic-information")).click();
    }

    private void clickCommentsTab() {
        driver.findElement(By.id("comments")).click();
    }

}
