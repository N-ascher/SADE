package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.SeleniumTestCase;
import br.com.unicamp.sade.cucumber.selenium.pages.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.UsersManagementPage;
import br.com.unicamp.sade.fixtures.UserFixture;
import br.com.unicamp.sade.service.dto.UserDTO;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersManagementStepDefs extends SeleniumTestCase {

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

    @When("^i delete the user \"([^\"]*)\"")
    public void deleteUser(String userLogin) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        UsersManagementPage usersManagementPage = menuPage.gotToUsersManagement();

        assertTrue(usersManagementPage.exists(userLogin));

        usersManagementPage.deleteUser(userLogin);
    }

    @Then("^the user \"([^\"]*)\" should not exist")
    public void userDeleted(String userLogin) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        UsersManagementPage usersManagementPage = menuPage.gotToUsersManagement();

        assertFalse(usersManagementPage.exists(userLogin));
    }

}
