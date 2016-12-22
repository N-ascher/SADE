package br.com.unicamp.sade.cucumber.selenium.pages;

import br.com.unicamp.sade.service.dto.UserDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.stream.Collectors;

public class ViewUserPage {

    private WebDriver driver;

    public ViewUserPage(WebDriver driver) {
        this.driver = driver;
    }

    public String login() {
        return driver.findElement(By.cssSelector("dd:nth-child(2)")).getText();
    }

    public String firstName() {
        return driver.findElement(By.cssSelector("dd:nth-child(4)")).getText();
    }

    public String lastName() {
        return driver.findElement(By.cssSelector("dd:nth-child(6)")).getText();
    }

    public String email() {
        return driver.findElement(By.cssSelector("dd:nth-child(8)")).getText();
    }

    public boolean active() {
        return Boolean.valueOf(driver.findElement(By.cssSelector("dd:nth-child(10)")).getText());
    }

    public String langKey() {
        return driver.findElement(By.cssSelector("dd:nth-child(12)")).getText();
    }

    public Set<String> authorities() {
        return driver.findElement(By.cssSelector("dd:nth-child(22)"))
                .findElements(By.tagName("span"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toSet());
    }

    public UserDTO getUser() {
        return new UserDTO(login(), firstName(), lastName(), email(), active(), langKey(),
                authorities());
    }
}
