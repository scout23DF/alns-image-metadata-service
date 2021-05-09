# ALNS :: UP42 Coding Challenge
This is a simple project built from scratch by Andre Nascimento to accomplish what was required in Code Challenge for a position in UP42.

## The Story
For this challenge, we want you to build a simple backend application that is capable of working with image metadata, that
is typically generated on the UP42 platform.
The goal is to expose three endpoints for listing features, retrieving a specific feature by its ID and to return an actual
image from a base64 encoded string.

The given data set has been generated on our platform. It contains a list of features, each representing an actual image,
with its associated metadata.

## How to Run
In order to run it, follow the instructions described in the next sections.

### Pre-Requisites
In order to run this simple application make sure you meet these pre-requisites:

- Git 2.25+
- Java JDK 11 (recommended AdoptOpenJDK)
- Maven 3.6.3+
- Some Java IDE (Optional);
- Postman (Optional).

### Steps to Run
- Clone the project on your local machine:
    - `> git clone https://github.com/scout23DF/alns-image-metadata-service.git`
- Move to the directory wher the project was cloned:
    - `> cd alns-image-metadata-service`
- [Optional] Alternatively, you also may import an run this project in your preferred IDE that supports Maven projects (IntelliJ Idea / Eclipse (STS) / NetBeans / VS Code + Java Extensions);
- Build, Package and perform the tests (Unit Tests and Integration Tests) using Maven 3.6.3+:
    - `> clear && mvn clean install`
    - Be patient and grab a Coffee whilst all dependencies and tests run :-)
    - Check if all tests ran successfully.
- In order to run the application, choose one of the following ways:
    - Using Spring Boot Maven Plugin:
        - `> mvn spring-boot:run`
    - Running as a Jar application:
        - `> clear && java -jar ./target/alns-image-metadata-service-1.0.0-SNAPSHOT.jar`
    - Run inside your preferred IDE.
    
### Inspecting the REST API's Endpoints:

You can inspect all the available REST API's navigating to nice Swagger-UI 3.0.0 at:

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)



