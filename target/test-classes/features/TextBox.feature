Feature: TextBox Functionality on DemoQA

  Scenario: User submits the Text Box form successfully
    Given user in on the Homepage
    When user navigates to the Elements section
    And user clicks on the Text Box option
    When user enters full name "John Doe"
    And user enters email "john@example.com"
    And user enters current address "123 Street, Bengaluru"
    And user enters permanent address "456 Avenue, Delhi"
    And user clicks the submit button
    Then the output data should match the entered data