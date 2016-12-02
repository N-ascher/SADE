Feature: Edit a user profile
  As a user registered at SADE
  I want to edit my profile
  So that I can update my info

  Scenario: results are shown
    Given the page is open 1 "http://localhost:8080/#/"
    When i sign in and edit my information
    Then the new information shows up at my profile
    
    
    