Feature: Only accept safe usernames at the signup
  As a malicious user
  I want to create a malicious username
  So that he can access privileged information

  Scenario: sql injection on login password
    Given i am a malicious user
    When sign up with a malicious username " ' or 1=1; -- SELECT * FROM user "
    Then the username should not be accepted