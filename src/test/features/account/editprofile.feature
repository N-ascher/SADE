@acceptance
Feature: Edit a developer profile
  As a developer registered at SADE
  I want to edit my profile
  So that I can update my info

  Scenario: results are shown
    Given the username is "user" and the password is "user"
    When i edit my profile settings
    Then the new information shows up at my profile