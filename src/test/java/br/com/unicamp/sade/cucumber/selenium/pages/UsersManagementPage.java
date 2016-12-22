package br.com.unicamp.sade.cucumber.selenium.pages;

import br.com.unicamp.sade.service.dto.UserDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsersManagementPage {

    private WebDriver driver;

    public UsersManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createUser(UserDTO userDTO) {
        driver.findElement(By.id("add-user")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.name("login")));

        driver.findElement(By.name("login")).sendKeys(userDTO.getLogin());
        driver.findElement(By.name("firstName")).sendKeys(userDTO.getFirstName());
        driver.findElement(By.name("lastName")).sendKeys(userDTO.getLastName());
        driver.findElement(By.name("email")).sendKeys(userDTO.getEmail());

        Select langKeySelect = new Select(driver.findElement(By.name("langKey")));
        langKeySelect.selectByVisibleText(userDTO.getLangKey());

        Select authoritySelect = new Select(driver.findElement(By.name("authority")));
        userDTO.getAuthorities().forEach(authority -> {
            authoritySelect.selectByVisibleText(authority);
        });

        WebElement submitButton = driver.findElement(By.id("save-new-user"));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(submitButton));
        submitButton.submit();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.className("alert-success")));
    }

    public UserDTO getLastUser() {
        driver.findElement(By.cssSelector("#users table tbody tr:last-child td:last-child button.btn-info")).click();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.id("user-detail")));

        List<WebElement> fields = driver.findElements(By.tagName("dd"));

        String login = fields.get(0).getText();
        String firstName = fields.get(1).getText();
        String lastName = fields.get(2).getText();
        String email = fields.get(3).getText();
        boolean active = Boolean.valueOf(fields.get(4).getText());
        String langKey = fields.get(5).getText();
        Set<String> authorities = fields.get(fields.size() - 1).findElements(By.tagName("span"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toSet());

        return new UserDTO(login, firstName, lastName, email, active, langKey, authorities);
    }

    public int numberOfUsers() {
        return driver.findElements(By.cssSelector("#users table tbody tr")).size();
    }

    public void deleteUser(String userLogin) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#users table tbody tr"));
        rows.stream()
                .filter(row -> row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals(userLogin))
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

    public boolean exists(String login) {
        return driver.findElements(By.cssSelector("#users table tbody tr td:nth-child(2)"))
            .stream()
            .anyMatch(webElement -> webElement.getText().equals(login));
    }
}
