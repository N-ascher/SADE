package br.com.unicamp.sade.cucumber.selenium.pages;

import br.com.unicamp.sade.cucumber.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

@PageObject
public class MenuPage {
    @Autowired
    WebDriver driver;

    @Autowired
    LoginPage loginPage;

    @Autowired
    DevelopersManagementPage developersManagementPage;

    @Autowired
    UsersManagementPage usersManagementPage;

    public DevelopersManagementPage gotToDevelopersManagement() {
        clickAdminMenu();

        driver.findElement(By.id("developers-management")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.id("developers")));

        return developersManagementPage;
    }

    public UsersManagementPage gotToUsersManagement() {
        clickAdminMenu();

        driver.findElement(By.id("users-management")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.id("users")));

        return usersManagementPage;
    }

    public LoginPage gotToLogin() {
        clickAccountMenu();

        driver.findElement(By.id("login")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(driver.findElement(By.id("username"))));
        return loginPage;
    }

    private void clickAdminMenu() {
        driver.findElement(By.id("admin-menu")).click();
    }

    private void clickAccountMenu() {
        driver.findElement(By.id("account-menu")).click();
    }
}
