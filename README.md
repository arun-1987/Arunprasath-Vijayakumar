# Arunprasath-Vijayakumar
Repo contains solution for Moneyfy and Automation code for Api testing


**Framework Overview:**
It is a customized hybrid framework, built using **Maven, TestNg, Selenium, Restassured and appium and Java as programming language**. I have designed this framework using design pattern Factory pattern for browser implementation and page object model for test automation. It is developed out of my own interest.

Git clone this project and run the test using maven command.

**To run (with Maven)**
mvn clean verify
 
Test File path - src\test\java\com\restassured\api\tests\PetStoreE2EIT.java

List of Test Cases Taken for Testing : 
1.createCustomer
2.adminAddingNewPet
3.adminUpdateExistingPet
4.adminDeleteUser
5.loginAsCustomer
6.searchPetByID
7.placeOrder
8.logoutAsCustomer

Total eight test cases. These tests were designed to achieve e2e automation which covers three major functionality areas. Inventory,User,Pet.
Test will be executed in the order which matches the real time scenario.

