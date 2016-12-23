package br.com.unicamp.sade.cucumber.selenium.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileSettingsPage {
    private WebDriver driver;

    public ProfileSettingsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstName(String firstName) {
        WebElement input = driver.findElement(By.name("firstName"));
        input.clear();
        input.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        WebElement input = driver.findElement(By.name("lastName"));
        input.clear();
        input.sendKeys(lastName);
    }

    public void setEmail(String email) {
        WebElement input = driver.findElement(By.name("email"));
        input.clear();
        input.sendKeys(email);
    }

    public void submit() {
        driver.findElement(By.cssSelector("#settings button.btn-primary")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(driver.findElement(By.className("alert-success"))));
    }

    public String firstName() {
        return driver.findElement(By.name("firstName")).getAttribute("value");
    }

    public String lastName() {
        return driver.findElement(By.name("lastName")).getAttribute("value");
    }

    public String email() {
        return driver.findElement(By.name("email")).getAttribute("value");
    }
}
