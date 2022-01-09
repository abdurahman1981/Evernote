# Evernote Front-end UI Testing Project
This project was Developed automation test scripts using Selenium WebDriver with Java in Intellij IDE.
## Automation test cases
- ### Front-End
1. User with invalid email should not be able to login
2. User with valid email should be able to login
3. User should be able to create new note
4. Newly created note should be opened after login
5. User should be able to delete newly created note

Notes: As I usual practice I added Delete created note test case. It keeps the application always clean and away from unneeded information. 

### When I create my project, First of all, I managed all my dependencies in POM.xml file.
![pom.xml file](https://user-images.githubusercontent.com/92171994/148689965-4d6c635e-8a16-4950-b150-e33fa3f0f0cb.png)

### Then I created a Utility package for my utilities where I would store all page files or utilities that I need when I execute my tests.
#### BasePage class includes functions below:
* setUpBrowser( );
* closeBrowser( );
#### UtilityPage class includes functions below:
* readConfig( );
* waitElementPresent( );
* waitElementClickabel( );
* sleep( );
* captureImage( );
#### TestDataHolder class includes functions below:
* setNoteTitle( );
* getNoteTitle( );
#### Log class includes functions below:
* startTestCase( );
* endTestCase( );
* info( );
#### CustomResultListener class includes functions below:
* onTestFailure( );

![utility_package](https://user-images.githubusercontent.com/92171994/148690333-ad1a4c67-b9fd-4c72-946d-25c5057ffa42.png)

#### I also created config file and put all variables I need like login url, valid email, password etc and when I need it I call from config file instead of hard coding.
![config_file](https://user-images.githubusercontent.com/92171994/148691152-1939349f-b577-4bb2-82e6-5f724c828254.png)

#### I also added Log4j for generating extending report and logs when the project is running.
![log4j](https://user-images.githubusercontent.com/92171994/148691918-ab6f9d3b-54bf-4ca7-80a8-e40abea5a244.png)

#### I created TestDataHolder class for managing temporery variables like "Note Title".
![TestDataHolder](https://user-images.githubusercontent.com/92171994/148692086-f955f674-a425-4aaf-a1e5-2f987c2a70ad.png)

#### I added screenshot utility for capturing the screen image when test case runs fail, and the pictures will be saved in Screenshot directory in the project.
![screenshot](https://user-images.githubusercontent.com/92171994/148692225-fac3a380-6617-4433-813e-5ef00778d1f5.png)

#### Then  I created separated Java Class for each page of my application where I store all elements of that page as well as related methods. I used POM pattern in order to keep my code organized and clean.
![POM_pages](https://user-images.githubusercontent.com/92171994/148690333-ad1a4c67-b9fd-4c72-946d-25c5057ffa42.png)

## Frameworks

- ### Cucumber Framework
#### I created Cucumber Feature Files where I used Gherkin language in order to describe my test scenario, by doing that my test cases are understandable for each member of my team.
![feature_file](https://user-images.githubusercontent.com/92171994/148692603-2d8c61eb-1de1-45a6-b679-9292c63da68d.png)

#### I created GlobalHooks class for running Setup and TearDown methoeds before and after All scenario.
![globalHooks](https://user-images.githubusercontent.com/92171994/148692745-d68ca74c-9e68-4b97-a7cc-f669f06b222b.png)

#### I put All step definitions in StepDefinition class. It can be separated diffirent classes as well.
![stepDefinitions](https://user-images.githubusercontent.com/92171994/148692737-f8593bf6-39ef-40f1-ac2c-daacc13591d7.png)

#### And I created CucumberTestRunner class
![cucumberRunner](https://user-images.githubusercontent.com/92171994/148694224-76d7ed24-a1bc-465f-82ad-a51673a6b272.png)

- ### TestNG Framework
#### I created TestNGTestRunner class
![testNGRunner](https://user-images.githubusercontent.com/92171994/148693174-48399369-1186-4468-86f3-76b029c88ca7.png)

#### I also created TestNG.xml file for generating emaiable testNG report, and It alos can be used in running project by mvn command and on Jenkins server.
![testNG_xml](https://user-images.githubusercontent.com/92171994/148694153-a2a79d74-c33b-45b6-8dea-32ecdb88544e.png)

#### Generated TestNG emailable Report
![testNGReport](https://user-images.githubusercontent.com/92171994/148693447-5d7243f4-e6ad-4cd7-be13-a0cc36a29c25.png)










