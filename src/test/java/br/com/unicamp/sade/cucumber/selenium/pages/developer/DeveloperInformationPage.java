package br.com.unicamp.sade.cucumber.selenium.pages.developer;

import br.com.unicamp.sade.cucumber.selenium.pages.interview.InterviewDialogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeveloperInformationPage {
    private WebDriver driver;

    public DeveloperInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    public EditDeveloperPage edit() {
        driver.findElement(By.id("edit-developer")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.className("modal-dialog")));
        return new EditDeveloperPage(driver);
    }

    public String email() {
        return driver.findElement(By.id("email")).getText();
    }

    public String login() {
        return driver.findElement(By.id("login")).getText();
    }

    public String phoneNumber() {
        return driver.findElement(By.id("phoneNumber")).getText();
    }

    public String mobileNumber() {
        return driver.findElement(By.id("mobileNumber")).getText();
    }

    public String document() {
        return driver.findElement(By.id("document")).getText();
    }

    public String street() {
        return driver.findElement(By.id("street")).getText();
    }

    public String neighborhood() {
        return driver.findElement(By.id("neighborhood")).getText();
    }

    public String state() {
        return driver.findElement(By.id("state")).getText();
    }

    public String city() {
        return driver.findElement(By.id("city")).getText();
    }

    public String complement() {
        return driver.findElement(By.id("complement")).getText();
    }

    public String number() {
        return driver.findElement(By.id("number")).getText();
    }

    public String postalCode() {
        return driver.findElement(By.id("postalCode")).getText();
    }

    public InterviewDialogPage addInterview() {
        driver.findElement(By.id("interview")).click();
        driver.findElement(By.id("add-interview")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.className("modal-dialog")));

        return new InterviewDialogPage(driver);
    }
}
