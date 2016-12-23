Feature: Deny acess to unauthorized users
  As an user
  I want to acess an out of bounds page
  So that he can have access to sensible informations

  Scenario: access to user management without permission
    Given the username is "user" and the password is "user"
    When i navigate to "/#/user-management"
    Then an access denied error should be shown

  Scenario: access to developer management without permission
      Given the username is "user" and the password is "user"
      When i navigate to "/#/developer"
      Then an access denied error should be shown

  Scenario: access to interview pre defined questions management without permission
      Given the username is "user" and the password is "user"
      When i navigate to "/#/pre-defined-question"
      Then an access denied error should be shown

  Scenario: access to audit without permission
      Given the username is "user" and the password is "user"
      When i navigate to "/#/audits"
      Then an access denied error should be shown