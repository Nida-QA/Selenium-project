# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* TestNG + Cucumber Based Automation Framework for Web.
* Framework is a combination of  **BDD + TestNG**.
* Approach is re-usability of .feature steps with configuration level.
* All element locators defined under enum files which are readable dynamically on runtime.
* Test Data defined under **resources/TestData/[fileName].properties**
* File Names are unique thing in this architect. Which should be same for locators variables under **src/resources/Locators/[FileName].properties** and Test Data mentioned above and for Enum under **src/main/java/EnumFactory/[FileName].enum**
* Variable for Locator should be same defined under .feature and Locators file entioned above.
* Variables under Locators file should be same mentioned under enum files
* Test Data Variable should be same under .feature and TestData files mentioned above.



### Execution Steps: ###
* To execute the project, navigate to **"Edit run/Debug configuration"** dropdown then edit the configuration and locate the **testRunner.xml** in TestNG project

### Execution Video: ###
* To see the execution of video navigate  **src/test/resources/ExecutionVideo** play through vlc player
