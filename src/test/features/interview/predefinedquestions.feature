Feature: Manage interview pre defined questions
    As a conpec user
    I want to manage interview pre defined questions
    So that other conpec users can use these questions on interviews

Scenario: Add question
    Given the username is "admin" and the password is "admin"
    When i add a new pre defined question "Qual acontecimento mais marcou você?"
    Then the pre defined question "Qual acontecimento mais marcou você?" should be available

Scenario: Update question
    Given the username is "admin" and the password is "admin"
    When i update the pre defined question "Como você se imagina daqui a 5 ou 10 anos?" to "Qual o acontecimento mais importante da sua vida"
    Then the pre defined question "Qual o acontecimento mais importante da sua vida" should be available
    And the pre defined question "Como você se imagina daqui a 5 ou 10 anos?" should not be available