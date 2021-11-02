Feature: Pizza tests
  Some tests for pizza search

  Background:
    Given user navigates to http://google.com

  Scenario: There are results for pizza
    When the user enters "pizza" and hits Enter
    Then number of results more than zero

  Scenario: There are no bad pizzas
    When the user enters "bad pizza" and hits Enter
    Then no results are shown