Feature: Add interview information to a developer
    As a conpec user
    I want to add interview information to a developer
    So that the interview information is available to other conpec users

Scenario: Developer information update by him/herself
    Given the username is "admin" and the password is "admin"
    When i add interview information to "dev" developer
    Then the interview information should be available