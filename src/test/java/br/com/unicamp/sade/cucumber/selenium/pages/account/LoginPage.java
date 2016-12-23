package br.com.unicamp.sade.cucumber.selenium.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String password) {
        setUsername(user);
        setPassword(password);
        submit();

        waitForSuccess(user);
    }

    public void waitForSuccess(String user) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(By.className("alert-success"), "Você está logado como \"" + user + "\"."));
    }

    public void submit() {
        driver.findElement(By.id("loginSubmit")).click();
    }

    public void setPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void setUsername(String user) {
        driver.findElement(By.id("username")).sendKeys(user);
    }

    public void waitForError() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.className("alert-danger")));
    }

    public boolean isError() {
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }

    public ForgotPasswordPage forgotPassword() {
        driver.findElement(By.id("forgot-password")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.id("reset-password-request")));
        return new ForgotPasswordPage(driver);
    }
}
