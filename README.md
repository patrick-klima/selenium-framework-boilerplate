
# Selenium Framework Boilerplate

This project aims to provide a modern Selenium WebDriver skeleton for writing maintainable tests. New projects can clone this code and start with a solid foundation.

## Features

* **.YAML Object Repository**
    * Locators are saved in a *.yaml file
    * Get Locators from within your Test classes
    * Able to change locators without recompilation
    * Manages Yaml Files
* **Containerized**
    * Throw-away containers are spinned up before every test
    * No need to manage WebDrivers on your own
    * Ensures 100% isolation and a consistent, controlled and modifiable test environment
* **Parallelization**
    * Run multiple tests at the same time
    * Driver instances are handled by the DriverManager static class
* **Retry Tests**
  * Retry test once more when failed
  * Ensures that test result is not a false negative
* **Page Object Pattern**
* **Logging**
* **AssertJ Assertions**

## Object Repository

All locators are saved in .yaml files. Why use .yaml files for managing locators?
* All locators in one single place.
* Quickly change faulty locators with your editors search function (CTRL+F)
* Allows nesting for higher readability (Compared to property files)
* Recompilation is not needed (Compared to hard-coded locators in POM classes)

yaml files are easier for humans to read and write, compared to other data formats such as json.  
Example object repository yaml file:
```yaml
the-internet:  
  login:  
    username: "id:username"  
    password: "id:password"  
  result:  
      result-message: "class:success"
```

Locators can be fetched using the ``getElement`` method in the Object Repository static class. Both ``.`` and ``/`` can be used for nesting.
```java
ObjectRepository.getLocator("login/username") //returns By.id("username")
ObjectRepository.getLocator("login/password") //returns By.id("password")
ObjectRepository.getLocator("login/result/result-message") //returns By.class("success")
```

Now using the ``getLocator`` method whenever you need a locator for ``driver.findElement()`` is quite messy, this is why the static class ``DriverUtils`` is included.

```java
//Example Test using the Object Repository without page object pattern

@Test(testName = "Can login with valid data", groups = {"smoke"})  
public void canLoginWithValidData() {  
    DriverUtils.findElement("login/username").sendKeys("tomsmith");
    DriverUtils.findElement("login/password").sendKeys("mySecretPW!");
    DriverUtils.findElement("login/password").submit();
	
    assertThat(DriverUtils.checkElementExist("login/result/result-message")).isTrue();  
}
```

DriverUtils acts as an extension for WebDriver to add additional functionality shared by all test classes.

## Getting Started

### Dependencies

* Docker (can be installed on Windows)
* Selenoid Instance running on Docker

### Installing

#### Selenoid Installation
Guide: https://aerokube.com/selenoid/latest/
#### Boilerplate
```
> git clone https://github.com/patrick-klima/selenium-framework-boilerplate.git
> move selenium-framework-boilerplate <YOUR_PROJECT_NAME>
```
Now, open the project in the IDE of your choice (IntelliJ IDEA for me), and sync maven packets. Modify TestNG configuration file (dev.xml & prod.xml) as necessary.

Use the existing locators.yaml file located in src/main/resources/locators.yaml, or copy and paste it to another path. If the path to the yaml file is different, open the testNG configuration file and change the ``objectRepository`` parameter to the new path.
