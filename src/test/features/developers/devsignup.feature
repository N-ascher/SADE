Feature: Register at SADE as a developer
  As a developer
  I want to register at Sade
  So that I can get a job

  Scenario: developer sign up successfuly
    Given I am a developer on register page
    When I fill all the necessary information
    Then my registration should be complete