package br.com.unicamp.sade.cucumber.stepdefs;

import br.com.unicamp.sade.cucumber.selenium.pages.UserDialogPage;
import br.com.unicamp.sade.cucumber.selenium.pages.ViewUserPage;
import br.com.unicamp.sade.cucumber.selenium.pages.MenuPage;
import br.com.unicamp.sade.cucumber.selenium.pages.UsersManagementPage;
import br.com.unicamp.sade.fixtures.UserFixture;
import br.com.unicamp.sade.security.AuthoritiesConstants;
import br.com.unicamp.sade.service.dto.UserDTO;
import com.google.common.collect.ImmutableSet;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Optional;

import static br.com.unicamp.sade.cucumber.selenium.Driver.getCurrentDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsersManagementStepDefs {

    @When("^I create a new admin user on users management")
    public void iCreateANewAdminUserInUsersManagement() {
        UserDTO newUser = UserFixture.admin();

        MenuPage menuPage = new MenuPage(getCurrentDriver());

        UsersManagementPage usersManagementPage = menuPage.gotToUsersManagement();
        usersManagementPage.openCreateUserDialog().create(newUser);
    }

    @Then("^a new admin user should be registered")
    public void aNewAdminUserShouldBeRegisteredAndAbleToLogin() {
        UserDTO newUser = UserFixture.admin();

        MenuPage menuPage = new MenuPage(getCurrentDriver());

        UsersManagementPage usersManagementPage = menuPage.gotToUsersManagement();
        Optional<UserDTO> createdUserOpt = usersManagementPage.openUserInfo(newUser.getLogin())
                .map(ViewUserPage::getUser);
        assertTrue(createdUserOpt.isPresent());

        UserDTO createdUser = createdUserOpt.get();
        assertEquals(newUser.getLogin(), createdUser.getLogin());
        assertEquals(newUser.getFirstName(), createdUser.getFirstName());
        assertEquals(newUser.getLastName(), createdUser.getLastName());
        assertEquals(newUser.getEmail(), createdUser.getEmail());
        assertEquals(newUser.getLangKey(), createdUser.getLangKey());
        assertEquals(newUser.getAuthorities(), createdUser.getAuthorities());
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

    @Given("the user \"([^\"]*)\" is not admin")
    public void theUserIsNotAdmin(String userLogin) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        Optional<UserDialogPage> editDialogPageOpt = menuPage.gotToUsersManagement().openUserEditDialog(userLogin);
        assertTrue(editDialogPageOpt.isPresent());

        UserDialogPage editDialogPage = editDialogPageOpt.get();
        editDialogPage.deselectAuthorities(ImmutableSet.of(AuthoritiesConstants.CONPEC_USER));
        editDialogPage.submit();

        Optional<ViewUserPage> viewUserPageOpt = menuPage.gotToUsersManagement().openUserInfo(userLogin);
        assertTrue(viewUserPageOpt.isPresent());

        ViewUserPage viewUserPage = viewUserPageOpt.get();
        assertFalse(viewUserPage.authorities().contains(AuthoritiesConstants.CONPEC_USER));
    }

    @When("^i edit the user \"([^\"]*)\" to be an admin")
    public void editUserToBeAdmin(String userLogin) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        Optional<UserDialogPage> editDialogPageOpt = menuPage.gotToUsersManagement().openUserEditDialog(userLogin);
        assertTrue(editDialogPageOpt.isPresent());

        UserDialogPage editDialogPage = editDialogPageOpt.get();
        editDialogPage.selectAuthorities(ImmutableSet.of(AuthoritiesConstants.CONPEC_USER));
        editDialogPage.submit();
    }

    @Then("^the user \"([^\"]*)\" should be admin")
    public void theUserShouldBeAdmin(String userLogin) {
        MenuPage menuPage = new MenuPage(getCurrentDriver());

        Optional<ViewUserPage> viewUserPageOpt = menuPage.gotToUsersManagement().openUserInfo(userLogin);
        assertTrue(viewUserPageOpt.isPresent());

        ViewUserPage viewUserPage = viewUserPageOpt.get();
        assertTrue(viewUserPage.authorities().contains(AuthoritiesConstants.CONPEC_USER));
    }
}
