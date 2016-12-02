Feature: Deny acess to unauthorized users
  As an user
  I want to acess an out of bounds page
  So that he can have access to sensible informations

  Scenario: results are shown
    Given the page is open 6 "http://localhost:8080/#/"
    When i sign in and and navigate to "http://localhost:8080/#/user-management"
    Then an access denied error should be shown
    
    
    