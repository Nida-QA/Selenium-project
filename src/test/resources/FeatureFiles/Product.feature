Feature: Login with Swag Lab Application and Add/Remove Product from Cart

  Background:
    Given User Setup Web Browser Session

  @Add_Product @Remove_Added_Product
  Scenario: Successfully Add and Remove Product from Swag Labs Application

    When User Navigates to "Swag Labs Application" URL
    And User Enters "User Name" on "User Name Textbox" Field on "Swag Lab Login" Page
    And User Enters "User Password" on "User Password Textbox" Field on "Swag Lab Login" Page
    And User Click on "Login" Button on "Swag Lab Login" Page
    Then User Validates "Swag Lab Application" Title
    And User Click on "Add to Cart" Button on "Swag Lab Inventory" Page
    Then User Validates "Counter" of "Basket" On "Swag Lab Inventory" Page
    And User Click on "Basket" Button on "Swag Lab Inventory" Page
    And User Click on "Remove" Button on "Swag Lab Cart" Page
    Then User Validates "Remove" Element Not Displayed on "Swag Lab Cart" Page