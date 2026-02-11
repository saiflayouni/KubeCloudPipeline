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

## Git Workflow
1.  **Main**: Production-ready code.
2.  **Dev**: Integration branch for ongoing development.
3.  **Feature Branches**: `feature/sprint-xx-name` for specific tasks.

### Merging Instructions
- Open a Pull Request from your feature branch to `dev`.
- Ensure CI checks pass (to be added).
- Squash and merge.

## Next Steps (Sprint-02)
- Implement full REST API endpoints.
- Add business logic and validation.
- Integrate with a database (generic or specific per service).
- Add unit and integration tests.
