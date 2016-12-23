Feature: Retrieve a lost password
  As a user that is already registered at SADE
  I want to retrieve the password to my account
  So that i can access SADE again

  Scenario: reset password request
    Given i am in the forgot password page
    When i request a password reset for the email "admin@localhost"
    Then an email with instructions should be sent to me