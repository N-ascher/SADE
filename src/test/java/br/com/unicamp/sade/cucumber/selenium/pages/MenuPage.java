package br.com.unicamp.sade.cucumber.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuPage {

    private WebDriver driver;

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public DevelopersManagementPage goToDevelopersManagement() {
        clickAdminMenu();

        driver.findElement(By.id("developers-management")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.id("developers")));

        return new DevelopersManagementPage(driver);
    }

    public UsersManagementPage goToUsersManagement() {
        clickAdminMenu();

        driver.findElement(By.id("users-management")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.id("users")));

        return new UsersManagementPage(driver);
    }

    public LoginPage goToLogin() {
        clickAccountMenu();

        driver.findElement(By.id("login")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(driver.findElement(By.id("username"))));
        return new LoginPage(driver);
    }

    public ProfileSettingsPage goToProfileSettings() {
        clickAccountMenu();

        driver.findElement(By.id("account-settings")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.id("settings")));

        return new ProfileSettingsPage(driver);
    }

    private void clickAdminMenu() {
        driver.findElement(By.id("admin-menu")).click();
    }

    private void clickAccountMenu() {
        driver.findElement(By.id("account-menu")).click();
    }
}
