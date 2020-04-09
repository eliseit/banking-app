# Banking App

Demo project for a feature in a banking  web app.

## Setup

 Clone the Git repository locally.
 maven wrapper can be used for convenience to `build` the app
 once the jar is generated, simply start the app from command line with
 `java -jar savings-account-0.0.1-SNAPSHOT.jar`  
 
 Production ready features were added to the app as `actuator` endpoints
 
 [check them here](http://localhost:8080/actuator)
 
## Technologies used
 Tech stack is compose of:
 
 * Java 11
 * Spring Boot 2.x
 * Front end: Thymeleaf, HTML 5, CSS 3, Javascript
 * Maven dependency/build tool
  
## How to ...

### Build/Run the app
  
  prerequisites:
   
   * At least Java **11**
    
   Open a terminal and issue command: 
   
   ```./mvnw springboot:run```
   
   Once the app is up and running simply visit [localhost](http://localhost:8080)
   
   Once the page is loaded you can visit the `MyAccount` section where new savings accounts
   can be created!
   
   Take note if you need to login, please use the username `user` and the password that is 
   shown in the logs of starting the app:
   
   ```Using generated security password: 5de65cbc-be25-4089-9337-dfc5097af9f5```

### Data
   Access to H2 Database console is enabled for development purpose and is found [here](http://localhost:8080/h2-console/)
## Requirements

Model `USER` entity for which an option to crate a new `ACCOUNT` of type `SAVINGS`.
Business requirements:

+ `Savings Account` can be opened only** in **WORKING DAYS/HOURS**, aka. Banking day
    
+ Only ONE account of this type can be opened per `USER`

