# My Spring App

A Spring Boot + Thymeleaf web application with an interactive in-memory task manager.

## Features

- **Welcome page** with a configurable greeting message
- **Task Manager** – view, add, and filter tasks in memory (no database required)
  - Add a task using the form on the home page
  - Filter the task list by keyword using the filter box

## Running the App

### Prerequisites
- Java 8+
- Maven 3.x

### Run locally

```bash
mvn spring-boot:run
```

Then open [http://localhost:8080](http://localhost:8080) in your browser.

### Run tests

```bash
mvn test
```

### Build a JAR

```bash
mvn clean package
java -jar target/app.jar
```

### Docker

```bash
docker build -t my-spring-app .
docker run -p 8080:8080 my-spring-app
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/` | Home page with task list (supports `?filter=keyword`) |
| POST | `/` | Add a new task (form param: `task`) |
| GET | `/hello?name=...` | Greeting page |
| GET | `/health` | JSON health/diagnostic endpoint |

## Azure Resources

Trying to get started with Java on Azure? See these resources:

- [Azure Samples](https://github.com/azure-samples?q=java&type=&language=&sort=)
- [Java on App Service Quickstart](https://docs.microsoft.com/en-us/azure/app-service/quickstart-java?tabs=javase&pivots=platform-linux)
- [Java and CosmosDB Tutorial](https://docs.microsoft.com/en-us/azure/app-service/tutorial-java-spring-cosmosdb)
- [Maven plugins](https://docs.microsoft.com/it-it/java/api/overview/azure/maven/azure-webapp-maven-plugin/readme?view=azure-java-stable#quick-start)
