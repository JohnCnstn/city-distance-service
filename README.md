# City Distance Service

A microservice which calculates the distance between two cities. The microservice contains two endpoints:
one endpoint which allows users to define a distance between two arbitrary cities, and a
second endpoint, that allows the user to retrieve the distance between two cities.

## Architecture

Neo4j was chosen as the database. It's an ACID-compliant transactional database with 
native graph storage and processing.

Swagger was chosen for designing, building, documenting and consuming REST APIs.

Kotlin was chosen for configuring Gradle.  It aims to provide Gradle users with a rich, flexible and 
statically-typed approach to developing build logic in conjunction with the best IDE 
and tooling experience possible.

Concurrent writes and reads do not cause inconsistencies in queries because of transactions:
[Transaction management in Neo4j](https://neo4j.com/docs/java-reference/current/transactions/#transactions-isolation)

Better performance was achieved by adding pagination while getting paths. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for 
development and testing purposes.

### Prerequisites

Project requires Java 11, Make, Docker.

### Installing

There are different ways to install required technologies, depends on OS. For MacOS:

Instructions for Java:
[AdoptOpenJDK installation](https://adoptopenjdk.net/installation.html?variant=openjdk11)

Instructions for Docker:
[Install Docker for Mac](https://docs.docker.com/v17.12/docker-for-mac/install/)

### Running

Project requires running Neo4j database and Swagger UI.

```
make docker-local
```

Then the project should be started, with the following command:

```
./gradlew bootRun
```

## Running the tests

```
./gradlew test
```