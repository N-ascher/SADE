Feature: Create a new CONPEC admin
  As a CONPEC admin
  I want to create a new admin user
  So that he can have admin privileges

  Scenario: results are shown
    Given the page is open 9 "http://localhost:8080/#/"
    When i sign in and edit an existing user to be a admin
    Then the user should become admin
    
    
    