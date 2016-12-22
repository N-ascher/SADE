package br.com.unicamp.sade.cucumber.selenium.pages;

import br.com.unicamp.sade.service.dto.UserDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class UserDialogPage {

    private WebDriver driver;

    public UserDialogPage(WebDriver driver) {
        this.driver = driver;
    }

    public UsersManagementPage create(UserDTO userDTO) {
        setLogin(userDTO.getLogin());
        setFirstName(userDTO.getFirstName());
        setLastName(userDTO.getLastName());
        setEmail(userDTO.getEmail());
        setLangKey(userDTO.getLangKey());
        selectAuthorities(userDTO.getAuthorities());

        submit();

        return new UsersManagementPage(driver);
    }

    public void submit() {
        WebElement submitButton = driver.findElement(By.id("save-new-user"));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(submitButton));
        submitButton.submit();

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.className("alert-success")));
    }

    public void selectAuthorities(Set<String> authorities) {
        Select authoritySelect = new Select(driver.findElement(By.name("authority")));
        authorities.forEach(authority -> {
            authoritySelect.selectByVisibleText(authority);
        });
    }

    public void deselectAuthorities(Set<String> authorities) {
        Select authoritySelect = new Select(driver.findElement(By.name("authority")));
        authorities.forEach(authority -> {
            authoritySelect.deselectByVisibleText(authority);
        });
    }

    public void setLangKey(String langKey) {
        Select langKeySelect = new Select(driver.findElement(By.name("langKey")));
        langKeySelect.selectByVisibleText(langKey);
    }

    public void setEmail(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
    }

    public void setLastName(String lastName) {
        driver.findElement(By.name("lastName")).sendKeys(lastName);
    }

    public void setFirstName(String firstName) {
        driver.findElement(By.name("firstName")).sendKeys(firstName);
    }

    public void setLogin(String login) {
        driver.findElement(By.name("login")).sendKeys(login);
    }
}
