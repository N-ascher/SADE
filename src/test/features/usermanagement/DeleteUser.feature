Feature: Delete a user at SADE
  As a CONPEC admin
  I want to delete an existing user
  So he cant sign in anymore

  Scenario: results are shown
    Given the page is open 8 "http://localhost:8080/#/"
    When i navigate to the registered users screen and delete a user
    Then the user should be deleted
    
    
    