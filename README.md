# Automation of Ingenico Connect API and Hosted Checkout webpage

This Project is a small POC of Serenity BDD framework using Cucumber, in Groovy and Java.
It uses Groovy RestClient for API automation and Selenium 3 webdriver for the web automation.
Maven has been used for dependency management.

**Table of Contents**
* [Functionality](#functionality)
* [Behavior Driven Development](#behavior-driven-development)
* [Testing Framework](#testing-framework)
* [How to Run the tests](#how-to-run-the-tests)
    * [Using Maven](#1-using-maven)
    * [Using IDE](#2-using-ide)
    * [Test Result](#3-test-result)



# Functionality
In order to complete a payment transaction using Ingenico Connect, the following operations are done:
1. Generate Authorization signature for the API
2. Invoke `CreateHostedCheckout` resource
3. Fetch the `partialRedirectUrl` from API response and form the payment URL
4. Navigate to the webpage in browser
5. Select `iDeal` payment method and pay as an `Issuer`
6. Complete the payment transaction

* All the above functionality are created in feature files (using Gherkin).
* Step definition are written in Java class.
* API methods are written in Groovy class.
* Webdriver methods are written in Java class.

# Behavior Driven Development

The test cases here in the automation framework are added in BDD format.
The reason for choosing to writ tests  in BDD format are:

		1) Currently many teams are using BDD in SDLC as Acceptance criteria are very well available for both Development and testing
		2) Using BDD Acceptance test Will help the business understand the test cases very easily

# Testing Framework

I have used BDD Test Automation framework Using SerenityBDD. This serenity BDD is a library which uses Cucumber and selenium for its development.
Reasons for choosing this framework:

		1) Easily maintainable automated acceptance criteria
		2) Living documentation of test results
		3) Opensource tool with huge support Online

# How to Run the tests

	# Pre-requisite:
	* JDK 8
	* Maven is installed in the machine and configured properly


## 1) Using Maven

	Open a command window and run:

		WindowsOS:	mvn clean verify

		MacOs : mvn clean verify -Dwebdriver.chrome.driver=DriverPathAsInput

	To Run Specific Tags of a test (Specific group of tests):

	  	mvn clean verify -Dcucumber.options="--tags @TAGNAME"

## 2) Using IDE
	Open the cloned project in IDE. Enable Auto-Import for Maven-dependency
	Run Testrunner files in the path (\src\test\java\runner\TestAutomationIT.java)


## 3) Test Result
Serenity BDD has one of the best reporting and the test reports are generated every time we execute the tests.
When the project is cloned into Local, test results reporting, along with screenshots, can be seen if we open **_`index.html`_** from `(target\site\serenity\index.html)`.

Kindly let me know if you need any further Information. You can contact me on my emailID.