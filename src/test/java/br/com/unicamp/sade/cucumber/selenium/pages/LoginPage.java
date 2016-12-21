package br.com.unicamp.sade.cucumber.selenium.pages;

import br.com.unicamp.sade.cucumber.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

@PageObject
public class LoginPage {

    @Autowired
    WebDriver driver;

    public void login(String user, String password) {
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginSubmit")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(By.className("alert-success"), "Você está logado como \"" + user + "\"."));
    }

}
