package br.com.unicamp.sade.cucumber.selenium.pages.home;

import br.com.unicamp.sade.cucumber.selenium.pages.account.LoginPage;
import br.com.unicamp.sade.cucumber.selenium.pages.account.ProfileSettingsPage;
import br.com.unicamp.sade.cucumber.selenium.pages.developer.DeveloperInformationPage;
import br.com.unicamp.sade.cucumber.selenium.pages.developer.DevelopersManagementPage;
import br.com.unicamp.sade.cucumber.selenium.pages.predefinedquestion.PreDefinedQuestionsManagementPage;
import br.com.unicamp.sade.cucumber.selenium.pages.user.UsersManagementPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.com.unicamp.sade.cucumber.selenium.SeleniumTestCase.baseUrl;

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

    public DeveloperInformationPage goToDeveloperInformation() {
        clickAccountMenu();

        //FIXME driver.findElement(By.id("dev-account-settings")).click();
        driver.get(baseUrl() + "/#/developer/1");
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.id("developer-detail")));
        return new DeveloperInformationPage(driver);
    }

    public PreDefinedQuestionsManagementPage goToPreDefinedQuestionsManagement() {
        clickAdminMenu();

        driver.findElement(By.id("pre-defined-question-management")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.id("pre-defined-questions")));

        return new PreDefinedQuestionsManagementPage(driver);
    }

    private void clickAdminMenu() {
        driver.findElement(By.id("admin-menu")).click();
    }

    private void clickAccountMenu() {
        driver.findElement(By.id("account-menu")).click();
    }
}
