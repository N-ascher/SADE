package br.com.unicamp.sade.cucumber.selenium.pages;

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
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginSubmit")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(By.className("alert-success"), "Você está logado como \"" + user + "\"."));
    }

}
