Feature: Update developer information
    As a developer who already registered
    I want to update my informations
    So that I can properly be chosen to a project

Scenario: Developer information update by him/herself
    Given the username is "user" and the password is "user"
    When i update my information
    Then it must lead me to updated information