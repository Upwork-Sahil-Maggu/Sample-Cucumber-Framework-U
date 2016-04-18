Feature: loginPage.feature

@MTOURS-1
Scenario: REG - User is able to login to the home page

Given The user is on Mercury Tours Login page
And user enters valid userid as 'validUserId' on the Login page
And user enters valid password as "validPassword" on the Login page
And user clicks on Login button on the Login page
Then verify that user is on the welcome page