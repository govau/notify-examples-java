# Notify Java example

This is a sample Java application to demonstrate integration to Notify
using the provided Java SDK. This is implemented as a Spring boot application


## Prerequisites
- Java 8


## Commands
To test: `./gradlew test`

To build (runs tests): `./gradlew build`

To run the Spring Boot application: `./gradlew bootRun`. This will bring up a Spring Boot application on port 8080.

## Endpoints
- You can make a http `POST` request against the `http://localhost:8080/hello-world` endpoint with a payload like:
```json
{
    "name": "Andrew",
    "age": 22
}
```