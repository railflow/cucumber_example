Feature: When I navigate to Railflow website I expect it to have some useful links.

  Background:
    Given user navigates to https://railflow.io

  Scenario: There is the "Register" link on the website
    Then Register link exists on the first page

  Scenario: Register link works
    When user clicks on Register link
    Then the register form is shown
    
