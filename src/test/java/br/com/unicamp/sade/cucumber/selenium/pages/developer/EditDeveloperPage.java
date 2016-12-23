package br.com.unicamp.sade.cucumber.selenium.pages.developer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditDeveloperPage {
    private WebDriver driver;

    public EditDeveloperPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setPhoneNumber(String phoneNumber) {
        WebElement input = driver.findElement(By.name("phoneNumber"));
        input.clear();
        input.sendKeys(phoneNumber);
    }

    public void setMobileNumber(String mobileNumber) {
        WebElement input = driver.findElement(By.name("mobileNumber"));
        input.clear();
        input.sendKeys(mobileNumber);
    }

    public void setDocument(String document) {
        WebElement input = driver.findElement(By.name("document"));
        input.clear();
        input.sendKeys(document);
    }

    public void setLinkedIn(String linkedIn) {
        WebElement input = driver.findElement(By.name("linkedIn"));
        input.clear();
        input.sendKeys(linkedIn);
    }

    public void setGitHub(String gitHub) {
        WebElement input = driver.findElement(By.name("gitHub"));
        input.clear();
        input.sendKeys(gitHub);
    }

    public void setAvailability(String availability) {
        WebElement input = driver.findElement(By.name("availability"));
        input.clear();
        input.sendKeys(availability);
    }

    public void setProspectedBy(String prospectedBy) {
        WebElement input = driver.findElement(By.name("prospectedBy"));
        input.clear();
        input.sendKeys(prospectedBy);
    }

    public void setStreet(String street) {
        WebElement input = driver.findElement(By.name("street"));
        input.clear();
        input.sendKeys(street);
    }

    public void setNumber(String number) {
        WebElement input = driver.findElement(By.name("number"));
        input.clear();
        input.sendKeys(number);
    }

    public void setNeighborhood(String neighborhood) {
        WebElement input = driver.findElement(By.name("neighborhood"));
        input.clear();
        input.sendKeys(neighborhood);
    }

    public void setCity(String city) {
        WebElement input = driver.findElement(By.name("city"));
        input.clear();
        input.sendKeys(city);
    }

    public void setState(String state) {
        WebElement input = driver.findElement(By.name("state"));
        input.clear();
        input.sendKeys(state);
    }

    public void setComplement(String complement) {
        WebElement input = driver.findElement(By.name("complement"));
        input.clear();
        input.sendKeys(complement);
    }

    public void setPostalCode(String postalCode) {
        WebElement input = driver.findElement(By.name("postalCode"));
        input.clear();
        input.sendKeys(postalCode);
    }

    public void submit() {
        driver.findElement(By.cssSelector(".modal-dialog button.btn-primary")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.className("modal-dialog")));
    }
}
