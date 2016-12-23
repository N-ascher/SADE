package br.com.unicamp.sade.cucumber.selenium.pages.user;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class UsersManagementPage {

    private WebDriver driver;

    public UsersManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    public UserDialogPage openCreateUserDialog() {
        driver.findElement(By.id("add-user")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.name("login")));

        return new UserDialogPage(driver);
    }

    public Optional<ViewUserPage> openUserInfo(String userLogin) {
        return findUserRowByLogin(userLogin)
            .map(userRow -> {
                userRow.findElement(By.cssSelector("td:last-child button.btn-info")).click();
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions
                                .elementToBeClickable(By.id("user-detail")));

                return new ViewUserPage(driver);
            }).findFirst();
    }

    public Optional<UserDialogPage> openUserEditDialog(String userLogin) {
        return findUserRowByLogin(userLogin)
            .map(userRow -> {
                userRow.findElement(By.cssSelector("td:last-child button.btn-primary")).click();
                new WebDriverWait(driver, 10)
                        .until(ExpectedConditions
                                .elementToBeClickable(By.name("login")));

                return new UserDialogPage(driver);
            }).findFirst();
    }

    public int numberOfUsers() {
        return driver.findElements(By.cssSelector("#users table tbody tr")).size();
    }

    public void deleteUser(String userLogin) {
        findUserRowByLogin(userLogin)
                .map(row -> row.findElement(By.cssSelector("td:last-child button.btn-danger")))
                .forEach(deleteButton -> {
                    deleteButton.click();
                    new WebDriverWait(driver, 10)
                            .until(ExpectedConditions
                                    .elementToBeClickable(By.className("modal-dialog")));
                    WebElement confirmButton = driver.findElement(By.cssSelector(".modal-dialog button.btn-danger"));
                    confirmButton.click();
                });

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.className("alert-success")));
    }

    private Stream<WebElement> findUserRowByLogin(String userLogin) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#users table tbody tr"));
        return rows.stream()
                .filter(row -> row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals(userLogin));
    }

    public boolean exists(String login) {
        return driver.findElements(By.cssSelector("#users table tbody tr td:nth-child(2)"))
            .stream()
            .anyMatch(webElement -> webElement.getText().equals(login));
    }
}
