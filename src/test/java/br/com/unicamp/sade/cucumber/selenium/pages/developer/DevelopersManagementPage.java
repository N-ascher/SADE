package br.com.unicamp.sade.cucumber.selenium.pages.developer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DevelopersManagementPage {

    private WebDriver driver;

    public DevelopersManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    public Optional<DeveloperInformationPage> editDeveloper(String devLogin) {
        return findDeveloperRowByLogin(devLogin)
                .map(userRow -> {
                    userRow.findElement(By.cssSelector("td:last-child button.btn-info")).click();
                    new WebDriverWait(driver, 10)
                            .until(ExpectedConditions
                                    .elementToBeClickable(By.id("developer-detail")));

                    return new DeveloperInformationPage(driver);
                }).findFirst();
    }

    private Stream<WebElement> findDeveloperRowByLogin(String devLogin) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#developers table tbody tr"));
        return rows.stream()
                .filter(row -> row.findElement(By.cssSelector("td:nth-child(2)")).getText().equals(devLogin));
    }
}
