package br.com.unicamp.sade.cucumber.selenium.pages.error;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccessDeniedPage {

    private WebDriver driver;

    public AccessDeniedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAccessDenied() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.id("access-denied")));
        return driver.findElement(By.cssSelector("#access-denied .alert-danger"))
                .getText()
                .equals("Você não tem autorização para acessar esta página.");
    }
}
