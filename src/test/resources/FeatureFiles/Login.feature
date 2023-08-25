Feature: Login with Swag Lab Application

  Background:
    Given User Setup Web Browser Session

  @Login
  Scenario: Successfully Logged In to Swag Labs Application

    When User Navigates to "Swag Labs Application" URL
    And User Enters "User Name" on "User Name Textbox" Field on "Swag Lab Login" Page
    And User Enters "User Password" on "User Password Textbox" Field on "Swag Lab Login" Page
    And User Click on "Login" Button on "Swag Lab Login" Page
    Then User Validates "Swag Lab Application" Title