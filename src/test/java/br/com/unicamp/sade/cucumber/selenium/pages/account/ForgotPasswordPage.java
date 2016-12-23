package br.com.unicamp.sade.cucumber.selenium.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
    }

    public void submit() {
        driver.findElement(By.cssSelector("#reset-password-request button.btn-primary")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.className("alert-success")));
    }

    public boolean isSuccess() {
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
}
