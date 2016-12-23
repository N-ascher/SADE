package br.com.unicamp.sade.cucumber.selenium.pages.home;

import br.com.unicamp.sade.cucumber.selenium.pages.developer.DeveloperSignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public DeveloperSignUpPage openDevSignUpPage() {
        driver.findElement(By.id("register")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.name("name")));
        return new DeveloperSignUpPage(driver);
    }
}
