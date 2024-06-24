# Hospital Reactive Microservices

Welcome to the Hospital Reactive Microservices project. This project is a collection of microservices that form a reactive system for managing hospital operations. It is designed to be a scalable and responsive solution for healthcare facilities.

## Project Structure

The project is organized into several modules, each responsible for specific functions:

- **Reactive Management Service**: Manages the overall operations of the hospital.
- **Core Reactive**: Core functionality and utilities used across the microservices.
- **ICU Reactive Service**: Manages the Intensive Care Unit.
- **Eureka Reactive Discovery Server**: Service discovery for the microservices.
- **Inpatient Unit Reactive Service**: Manages inpatient units within the hospital.
- **Gateway Reactive Service**: Gateway for routing requests to the appropriate microservices.
- **Config Server**: Configuration management for the microservices.

## Technologies Used

- **Spring Boot**: The microservices are built using Spring Boot, a popular Java framework for building microservices.
- **Reactive Programming**: The system is designed to be reactive, making use of reactive programming principles for responsiveness and scalability.
- **Spring Cloud**: Spring Cloud is used for service discovery, configuration management, and other cloud-related functions.
- **RabbitMQ**

## Dependencies


## Getting Started

To get started with the Hospital Reactive Microservices project, you need to have Java 11 or higher installed and a working Maven environment. You can build and run the microservices locally or deploy them to a cloud platform.

1. Clone the project from the [GitHub repository](https://github.com/yourusername/hospital-reactive-microservices).

2. Build the project using Maven:

```bash
mvn clean install
```

3. Run the individual microservices or use a container orchestration system like Docker and Kubernetes for deployment.

4. Access the services through their respective endpoints.

## Documentation

For detailed documentation and usage instructions for each microservice, please refer to the specific module's README and documentation files.

## Contributors

- Matheus Mota Gurgel Gurjão
