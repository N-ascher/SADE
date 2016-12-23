Feature: Make user admin
  As a CONPEC admin
  I want to make a user admin
  So that he can have admin privileges

  Scenario: make an user admin
    Given the username is "admin" and the password is "admin"
    And the user "system" is not admin
    When i edit the user "system" to be an admin
    Then the user "system" should be admin