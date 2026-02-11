#!/bin/bash
echo "Building all services..."

cd task-service && ./mvnw clean package -DskipTests
cd ../user-service && ./mvnw clean package -DskipTests
cd ../notification-service && ./mvnw clean package -DskipTests

echo "All services built successfully."
