package br.com.unicamp.sade.cucumber.selenium.pages;

import br.com.unicamp.sade.service.dto.DeveloperDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeveloperSignUpPage {

    private WebDriver driver;

    public DeveloperSignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void register(DeveloperDTO developer) {
        setName(developer.getUser().getFirstName() + " " + developer.getUser().getLastName());
        setPhoneNumber(developer.getPhoneNumber());
        setMobileNumber(developer.getMobileNumber());
        setDocument(developer.getDocument());

        setStreet(developer.getAddress().getStreet());
        setNeighborhood(developer.getAddress().getNeighborhood());
        setNumber(developer.getAddress().getNumber());
        setCity(developer.getAddress().getCity());
        setState(developer.getAddress().getState());
        setPostalCode(developer.getAddress().getPostalCode());
        setComplement(developer.getAddress().getComplement());

        next();

        setLogin(developer.getUser().getLogin());
        setEmail(developer.getUser().getEmail());
        setPassword(developer.getUser().getPassword());
        setConfirmPassword(developer.getUser().getPassword());

        next();

        setAvailability(developer.getAvailability());
        setProspectedBy(developer.getProspectedBy());
        setLinkedIn(developer.getLinkedIn());
        setGitHub(developer.getGitHub());
        developer.getTechnologies()
                .forEach(technology -> addTechnology(technology.getName(), technology.getScore()));

        register();
    }

    public void setName(String name) {
        driver.findElement(By.name("name")).sendKeys(name);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(By.name("phone")).sendKeys(phoneNumber);
    }

    public void setMobileNumber(String mobileNumber) {
        driver.findElement(By.name("mobileNumber")).sendKeys(mobileNumber);
    }

    public void setDocument(String document) {
        driver.findElement(By.name("document")).sendKeys(document);
    }

    public void setStreet(String street) {
        driver.findElement(By.name("street")).sendKeys(street);
    }

    public void setNeighborhood(String neighborhood) {
        driver.findElement(By.name("neighborhood")).sendKeys(neighborhood);
    }

    public void setNumber(String number) {
        driver.findElement(By.name("number")).sendKeys(number);
    }

    public void setCity(String city) {
        driver.findElement(By.name("city")).sendKeys(city);
    }

    public void setState(String state) {
        driver.findElement(By.name("state")).sendKeys(state);
    }

    public void setPostalCode(String postalCode) {
        driver.findElement(By.name("postalCode")).sendKeys(postalCode);
    }

    public void setComplement(String complement) {
        driver.findElement(By.name("complement")).sendKeys(complement);
    }

    public void setLogin(String login) {
        driver.findElement(By.name("login")).sendKeys(login);
    }

    public void setEmail(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
    }

    public void setAvailability(Integer availability) {
        driver.findElement(By.name("availability")).sendKeys(Integer.toString(availability));
    }

    public void setProspectedBy(String prospectedBy) {
        driver.findElement(By.name("prospectedBy")).sendKeys(prospectedBy);
    }

    public void setLinkedIn(String linkedIn) {
        driver.findElement(By.name("linkedIn")).sendKeys(linkedIn);
    }

    public void setGitHub(String gitHub) {
        driver.findElement(By.name("gitHub")).sendKeys(gitHub);
    }

    public void addTechnology(String name, Integer score) {
        driver.findElement(By.name("technologyName")).sendKeys(name);
        driver.findElement(By.name("technologyScore")).sendKeys(Integer.toString(score));

        driver.findElement(By.id("addTech")).click();
    }

    public void register() {
        driver.findElement(By.id("register")).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .elementToBeClickable(By.className("alert-success")));
    }

    public void next() {
        driver.findElement(By.id("next")).click();
    }

    public void previous() {
        driver.findElement(By.id("previous")).click();
    }

    public boolean isSuccess() {
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
}
