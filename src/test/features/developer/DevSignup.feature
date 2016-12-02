Feature: Register at SADE as a developer
  As a developer
  I want to register at Sade
  So that I can get a job

  Scenario: results are shown
    Given the register page is open "http://localhost:8080/#/register"
    When i fill all the necessary information
    Then my registration should be complete
    
    
    