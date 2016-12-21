package br.com.unicamp.sade.cucumber.users;

import br.com.unicamp.sade.cucumber.selenium.SeleniumTestCase;
import br.com.unicamp.sade.cucumber.selenium.pages.LoginPage;
import br.com.unicamp.sade.cucumber.selenium.pages.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.UsersManagementPage;
import br.com.unicamp.sade.fixtures.UserFixture;
import br.com.unicamp.sade.service.dto.UserDTO;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class CreateAdminUserScenarioTest extends SeleniumTestCase {

    @Autowired
    protected WebDriver driver;

    @Autowired
    private MenuPage menuPage;

    @Autowired
    private UsersManagementPage usersManagementPage;

    @Before
    public void setup() {
        driver.get(baseUrl());
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(By.tagName("h1"), "Bem vindo ao SADE!"));
    }

    @After
    public void finish() {
        driver.close();
    }

    @Given("^I am an admin user")
    public void iAmAnAdminUser() {
        LoginPage loginPage = menuPage.gotToLogin();
        loginPage.login("admin", "admin");
    }

    @When("^I create a new admin user on users management")
    public void iCreateANewAdminUserInUsersManagement() {
        UserDTO newUser = UserFixture.charmander();

        UsersManagementPage usersManagementPage = menuPage.gotToUsersManagement();
        usersManagementPage.createUser(newUser);
    }

    @Then("^a new admin user should be registered")
    public void aNewAdminUserShouldBeRegisteredAndAbleToLogin() {
        UserDTO newUser = UserFixture.charmander();

        UserDTO lastCreatedUser = usersManagementPage.getLastUser();

        assertEquals(newUser.getLogin(), lastCreatedUser.getLogin());
        assertEquals(newUser.getFirstName(), lastCreatedUser.getFirstName());
        assertEquals(newUser.getLastName(), lastCreatedUser.getLastName());
        assertEquals(newUser.getEmail(), lastCreatedUser.getEmail());
        assertEquals(newUser.getLangKey(), lastCreatedUser.getLangKey());
        assertEquals(newUser.getAuthorities(), lastCreatedUser.getAuthorities());
    }

}
