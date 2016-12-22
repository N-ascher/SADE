package br.com.unicamp.sade.cucumber.users;


import br.com.unicamp.sade.cucumber.selenium.SeleniumTestCase;
import br.com.unicamp.sade.cucumber.selenium.pages.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.UsersManagementPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteUserScenarioTest extends SeleniumTestCase {

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
