# AGONZALEZCASTILLO

## Installation

task_scheduler requires [java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) to run.

```sh
cd task_scheduler
./gradlew build
./gradlew bootRun 
```
## Getting Started
#### this service works on the premise that the [random task generator](https://github.com/bi-lriveros/schedulator) is running at localhost:8080

to obtain the assignments, once the service is up and running, do a GET request to the next URL
```sh
GET localhost:8090/api/tasks
```
