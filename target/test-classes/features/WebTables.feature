Feature: WebTable Functionality on DemoQA

  Scenario: Add a new user to the Web Table
    Given user launches the application
    And user clicks on the Elements
    When user is on the WebTables page
    And user click on the Add button
    And user enters Registration details "Alden" "Cantrell" "alden@example.com" "30" "10000" "Compliance"
    And user clicks the registration submit button
    Then the new user "alden@example.com" should appear in the search results