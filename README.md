# catawiki test automation  

This is a java project contains the gui automation of search functionality within catawiki web application.


## Table of Contents

- [Getting Started](#getting-started)
- [Tech Stack](#tech-stack)
- [Usage](#usage)
- [System Requirements](#system-requirements)
- [Steps to run the tests in local](#steps-to-run-the-tests-in-local)


## Getting Started

This project automates the below scenario

Scenario steps:
1. Open https://www.catawiki.com/en/
2. At the top of the page there is a search field:
   ○ type the keyword train
   ○ click magnifier button within a search field
3. Verify that the search results page is opened.
4. Click on the second lot in search results.
5. Lot’s page should be opened.
6. On the lot page, you'll see:
   ○ lot's name
   ○ “favorites” counter
   ○ current bid
7. Get the values of these items and print those values to console.

## Tech Stack

      Java Programming language is used due to its cross-platform compatibility, strong community support, and robust libraries, aligning well with Selenium's OOP design and Java's wide adoption.

      Selenium is used as it enables cross-browser testing, regression testing, and seamless integration with other tools.

      This automation framework also uses Cucumber, a BDD testing tool, to create human-readable scenarios for testing. Cucumber allows you to write feature files using Gherkin syntax and implement step definitions in your preferred programming language.

      Cucumber report is used for reporting.Attached the executed cucumber report under  target/cucumber-reports/cucumber/cucumber-html-reports/overview-features.html

## Usage

# TestRunner

    Test runner class define the run configuration like which feature,tags to be executed and what kind of report to be generated at which location.

# StepDefinitions

    Stepdefinition class is created for searchSteps which contains all the cucumber step implementation. This could be extended for multiple actions/pages.

    Utilized  `@FindBy` annotations provided by Selenium's PageFactory class to locate web elements within Page Objects. These annotations help associate elements with their corresponding Java fields, simplifying element initialization and usage.

    All page objects elements mentioned in the SearchSteps class could be maintained in a separate PageObject class/folders when the number of scenarios are increased.

## System Requirements

      Java : 17

## Steps to run the tests in local

Using IDE :

          1. Scenario can be run directly from the feature file by simply right clicking the editor-> Run Scenario : Search the lots
      
          2. Configure Junit in the run configuration and add TestRunner in the class and run the Junit configuration. 

Using command line:

          3. Use mvn test simply to run the test in default browser firefox or mvn test -Dtest.browser=chrome to pass different browser as argument.
