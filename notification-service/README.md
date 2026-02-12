# Notification Service

Spring-boot/Quarkus microservice responsible for handling asynchronous alerts (Email/SMS).

## 🔌 API Endpoints

| Method | Path | Description |
| :--- | :--- | :--- |
| `POST` | `/notifications/email` | Send an email |
| `GET` | `/notifications/health` | Service health check |

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
docker build -f notification-service/Dockerfile -t notification-service .
docker run -p 8083:8083 notification-service
```
