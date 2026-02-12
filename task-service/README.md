# Task Service

Spring-boot/Quarkus microservice responsible for creating, updating, and validtating Tasks.

## 🔌 API Endpoints

| Method | Path | Description |
| :--- | :--- | :--- |
| `GET` | `/tasks` | List all tasks |
| `GET` | `/tasks/{id}` | Get task details |
| `POST` | `/tasks` | Create a new task |
| `DELETE` | `/tasks/{id}` | Delete a task |

## 🛠 Local Development

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker

### Build & Run
```bash
# Debug Mode
./mvnw compile quarkus:dev

# Build JAR
./mvnw clean package
```

## 🐳 Docker

```bash
docker build -f task-service/Dockerfile -t task-service .
docker run -p 8081:8081 task-service
```
