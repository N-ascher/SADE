Feature: Make user admin
  As a CONPEC admin
  I want to make a user admin
  So that he can have admin privileges

  Scenario: results are shown
    Given the username is "admin" and the password is "admin"
    When i edit the user "user" to be an admin
    Then the user "user" should be admin