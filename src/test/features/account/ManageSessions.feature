Feature: Manage active sessions
  As a registered user
  I want to manage existing sessions
  So that i can choose which ones can still stay logged in

  Scenario: results are shown
    Given the page is open 3 "http://localhost:8080/#/"
    When i sign in, access the manage sessions page and delete an existing session
    Then the browser should not be logged in anymore after next access
    
    
    