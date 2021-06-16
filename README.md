# Mock Company E-Commerce Application

This is the e-commerce application for Mock Company.  It's built using the following languages/frameworks

| Language/Framework       | Usage | Source Location |
| ------------------------ | ------- | --------------- |
| [Java 8][java]           | API Services Language                                                                            | [src/main/java][main]                                                       |
| [Gradle 6][gradle]       | Automation framework used for orchestrating the building/testing/packaging of the app            | [build.gradle]()                                                            |
| [Spring][spring]         | Framework for API development and dependency injection among other things                        | [src/main/java][main]                                                       |
| [JPA][jpa]               | Java Persistence API - Java framework for data access                                            | [ProductItem][ProductItem] / [ProductItemRepository][ProductItemRepository] |
| [Groovy 2.4][groovy]     | Language for Gradle build file and writing unit tests                                            | [src/test/groovy][test]                                                     |
| [Spock][spock]           | Framework for writing unit tests in Groovy                                                       | [src/test/groovy][test]                                                     |
| [Typescript][typescript] | Language for generating type safe javascript                                                     | [client-app/src][client-app]                                                |
| [React][react]           | Framework for building dynamic User Interfaces                                                   | [client-app/src][client-app]                                                |
| [Material UI][material]  | React library for building User Interfaces that meet the [Material Design Spec][material-design] | [client-app/src][client-app]                                                |


## Development Environment

To develop against this codebase, the following development tools should be installed on your
workstation first:
 * [Java 8 JDK](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
 * [Node 12+](https://nodejs.org/en/download/)
 * [Git](https://github.com/git-guides/install-git)   
 * [GitHub](https://docs.github.com/en/github/getting-started-with-github/quickstart/set-up-git)

### IDE Setup

You can use your IDE of choice to develop in this app but it should have Java/Gradle support at
a minimum.  The best recommendation for IDE is [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/) as
it's free and works very well with Java/Gradle/Spring/Groovy/Spock.

## Getting Started

All build/test commands are to be run through a terminal using the [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) file.
This means, instead of each developer having to download and install the exact version of Gradle for the project,
there are shell scripts: [gradlew]() for posix (Mac/Linux) and [gradlew.bat]() for Windows. These shell scripts should be
used to execute any 'task', such as a `build`, `assemble`, or `test`.

For example, on Windows, to run all unit tests you would run: `gradlew test` and on Mac: `./gradlew test` from the root of the repository
where the gradlew files are located. On Windows, the `.bat` isn't needed because it automatically looks in the current working directory.  Where on Mac/Linux,
the developer has to use `./` at the beginning to indicate the script to run in the current working directory, indicated by the `.`

### Running the Application

To build and start the application, run the Gradle `bootRun` task

    ./gradlew bootRun  # on Mac/Linux
    gradlew bootRun    # on Windows

This will build the UI, build the Java API, and start the application at the following 
web address: http://localhost:8080

### Common Gradle Tasks

 * `test` - run all unit tests
 * `assemble` - build and package the application
 * `build` - runs `test` and `assemble`
 * `clean` - removes generated files/folders
 * `bootRun` - compiles and runs the application

[java]: https://docs.oracle.com/javase/8/docs/api/
[gradle]: https://docs.gradle.org/6.8.1/userguide/userguide.html
[main]: ./src/main/java
[jpa]: https://www.tutorialspoint.com/jpa/index.htm
[ProductItem]: ./src/main/java/com/mockcompany/webapp/model/ProductItem.java
[ProductItemRepository]: ./src/main/java/com/mockcompany/webapp/data/ProductItemRepository.java
[test]: ./src/test/groovy
[spring]: https://spring.io/projects/spring-framework
[groovy]: https://groovy-lang.org/
[spock]: https://spockframework.org/spock/docs/2.0/index.html
[typescript]: https://www.typescriptlang.org/docs/handbook/intro.html
[client-app]: ./client-app/src
[material]: https://material-ui.com/
[material-design]: https://material.io/
