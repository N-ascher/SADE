Feature: Register at SADE as a developer
  As a CONPEC admin
  I want to create a new admin
  So a new user can manage SADE

  Scenario: results are shown
    Given I am an admin user
    When I create a new admin user on users management
    Then a new admin user should be registered