Feature: Retrieve a lost password
  As a user that is already registered at SADE
  I want to retrieve the password to my account
  So that i can access SADE again

  Scenario: results are shown
    Given the page is open 2 "http://localhost:8080/#/"
    When i try to retrieve my password
    Then an email with instructions should be sent to me
    
    
    