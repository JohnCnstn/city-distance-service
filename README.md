# City Distance Service

A microservice which calculates the distance between two cities. The microservice contains two endpoints:
one endpoint which allows users to define a distance between two arbitrary cities, and a
second endpoint, that allows the user to retrieve the distance between two cities.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for 
development and testing purposes.

### Prerequisites

Neo4j was chosen as the database. It's an ACID-compliant transactional database with 
native graph storage and processing.

Swagger was chosen for designing, building, documenting and consuming REST APIs.

Kotlin was chosen for configuring gradle.  It aims to provide Gradle users with a rich, flexible and 
statically-typed approach to developing build logic in conjunction with the best IDE and tooling experience possible.

### Installing

Project requires java 11. There are different ways to install it, depends on OS. For MacOS:

```
brew cask install java11
```

### Running

Project requires running neo4j database and swagger UI.

```
make docker-local
```

Then the project should be started, with the following command:

```
./gradlew bootrun --args='--profile=local
```

## Running the tests

```
./gradlew test
```