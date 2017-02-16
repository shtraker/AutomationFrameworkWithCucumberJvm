# AutomationFrameworkWithCucumberJvm
This is Ready to use Automation Framework with Cucumber-JVM

Hey guys , my name is Cvetan Karchev and I'm from Bulgaria. I'm a QA Automation Lead and I'm educating young talents to became automation engineers in IT Academy. 

I decided to share my automation framework with everybody, so I can help others to learn how to do it. In your lifetime it comes time to give something back. Through so many years I've learned so much and now I want to give something back to the society. All I want from you is to do the same when the time comes. 

# This is Ready to Use Automation Framework with Cucumber-Jvm , Junit and Selenium.

For building the project I use Maven, you need to install it and configure it on your system, also Java JDK and Git.

Choose where you want to put this project on your PC, go to this directory with the shell and execute

git clone https://github.com/shtraker/AutomationFrameworkWithCucumberJvm

You will need Docker and to run a container for the Selenium Server. 

Check out this : 

https://github.com/SeleniumHQ/docker-selenium

I use this image for creating container: 

docker run -v -d -p 4000:4444 -p 5000:5900 selenium/standalone-firefox-debug:2.53.0 


# Structure of the Automation Framework 

Package – pageobjects – contains classes for all page objects on a page, use different classes for different pages.

Package – steps  - contains all classes for cucumber steps, use different classes and combine the steps by functionality.

Package – tools – here are all classes that contains the core functionality. 

DebugTestRunner – this class runs only one specific scenario , this is used for debug and develop new scenarios.

RunTestInParallel – runs all test scenarios in parallel , but first generate Junit Classes for every cucumber scenario.

Features directory contains all cucumber feature files. You can put them in different directories and etc. 
