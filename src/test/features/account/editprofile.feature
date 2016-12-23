Feature: Edit a user profile
  As a user registered at SADE
  I want to edit my profile
  So that I can update my info

  Scenario: edit profile successfully
    Given the username is "user" and the password is "user"
    When i edit my profile settings
    Then the new information shows up at my profile