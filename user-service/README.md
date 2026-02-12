# User Service

Spring-boot/Quarkus microservice responsible for managing User profiles and identities.

## 🔌 API Endpoints

| Method | Path | Description |
| :--- | :--- | :--- |
| `GET` | `/users` | List all users |
| `GET` | `/users/{id}` | Get user profile |
| `POST` | `/users` | Create a new user |
| `DELETE` | `/users/{id}` | Delete a user |

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
docker build -f user-service/Dockerfile -t user-service .
docker run -p 8082:8082 user-service
```
