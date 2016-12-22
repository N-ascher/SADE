Feature: Delete a user at SADE
  As a CONPEC admin
  I want to delete an existing user
  So he cant sign in anymore

  Scenario: results are shown
    Given the username is "admin" and the password is "admin"
    When i delete the user "charmander"
    Then the user "charmander" should not exist