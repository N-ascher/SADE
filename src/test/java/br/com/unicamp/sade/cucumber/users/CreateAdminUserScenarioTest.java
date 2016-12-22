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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.com.unicamp.sade.cucumber.selenium.Driver.closeCurrentDriver;
import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertEquals;

public class CreateAdminUserScenarioTest extends SeleniumTestCase {

    @Before
    public void setup() {
        getCurrentDriver().get(baseUrl());
        new WebDriverWait(getCurrentDriver(), 10)
                .until(ExpectedConditions
                        .textToBePresentInElementLocated(By.tagName("h1"), "Bem vindo ao SADE!"));
    }

    @After
    public void finish() {
        closeCurrentDriver();
    }

    @Given("^the username is \"([^\"]*)\" and the password is \"([^\"]*)\"$")
    public void theUserIsAndPasswordIs(String user, String password) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        LoginPage loginPage = menuPage.gotToLogin();
        loginPage.login(user, password);
    }

    @When("^I create a new admin user on users management")
    public void iCreateANewAdminUserInUsersManagement() {
        UserDTO newUser = UserFixture.charmander();

        MenuPage menuPage = new MenuPage(getCurrentDriver());

        UsersManagementPage usersManagementPage = menuPage.gotToUsersManagement();
        usersManagementPage.createUser(newUser);
    }

    @Then("^a new admin user should be registered")
    public void aNewAdminUserShouldBeRegisteredAndAbleToLogin() {
        UserDTO newUser = UserFixture.charmander();

        MenuPage menuPage = new MenuPage(getCurrentDriver());

        UsersManagementPage usersManagementPage = menuPage.gotToUsersManagement();
        UserDTO lastCreatedUser = usersManagementPage.getLastUser();

        assertEquals(newUser.getLogin(), lastCreatedUser.getLogin());
        assertEquals(newUser.getFirstName(), lastCreatedUser.getFirstName());
        assertEquals(newUser.getLastName(), lastCreatedUser.getLastName());
        assertEquals(newUser.getEmail(), lastCreatedUser.getEmail());
        assertEquals(newUser.getLangKey(), lastCreatedUser.getLangKey());
        assertEquals(newUser.getAuthorities(), lastCreatedUser.getAuthorities());
    }

}
