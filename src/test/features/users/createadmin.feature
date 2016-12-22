Feature: Create admin user
  As a CONPEC admin
  I want to create a new admin
  So a new user can manage SADE

  Scenario: results are shown
    Given the username is "admin" and the password is "admin"
    When I create a new admin user on users management
    Then a new admin user should be registered