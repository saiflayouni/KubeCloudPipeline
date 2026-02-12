# KubeCloudPipeline

A portfolio-ready multi-microservice demo project showcasing a modern cloud-native architecture.

## Sprint-01 Goals
- **Repository Initialization**: Set up Git structure, branches, and ignoring rules.
- **Local Environment**: Establish the baseline for local development.
- **Service Skeletons**: Create the initial structure for Task, User, and Notification services using Quarkus.
- **Git Workflow**: Define the branching strategy (Main -> Dev -> Feature).

## Project Structure
The project consists of the following microservices:

- **task-service**: Core service for managing tasks and workflows.
- **user-service**: Handles user profiles and authentication.
- **notification-service**: Manages email/push notifications.

Each service is a standalone Quarkus application with its own Dockerfile and configuration.

## Sprint-02 Recap (Completed)
- **Microservice Implementation**: Developed the `task-service` using Quarkus.
- **REST API**: Implemented full CRUD endpoints for Task management (`GET`, `POST`, `PUT`, `DELETE`).
- **Data Storage**: Integrated in-memory storage using `ConcurrentHashMap` for thread-safe operations.
- **Testing**: Added comprehensive unit tests with `QuarkusTest` and `RestAssured`, ensuring reliability and correct execution order.
- **Documentation**: Auto-generated OpenAPI/Swagger documentation available at `/swagger-ui`.

## Git Workflow
1.  **Main**: Production-ready code.
2.  **Dev**: Integration branch for ongoing development.
3.  **Feature Branches**: `feature/sprint-xx-name` for specific tasks.

### Merging Instructions
- Open a Pull Request from your feature branch to `dev`.
- Ensure CI checks pass (to be added).
- Squash and merge.

## Next Steps (Sprint-03)
- **CI/CD Pipeline**: Integrate with Google Cloud Build for automated testing and building.
- **Service Containerization**: refine Dockerfiles for production.
- **User Service Planning**: Begin architecture for `user-service` implementation.

# Verification Trigger
