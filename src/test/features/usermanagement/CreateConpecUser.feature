Feature: Register at SADE as a developer
  As a CONPEC admin
  I want to create a new admin
  So a new user can manage SADE

  Scenario: results are shown
    Given the page is open 7 "http://localhost:8080/#/"
    When i navigate to the create new admin dialogue and i fill all the necessary information
    Then a new admin should be registered
    
    
    