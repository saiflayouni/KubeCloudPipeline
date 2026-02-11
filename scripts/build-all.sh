#!/bin/bash
echo "Building all services..."

cd task-service && mvn clean package -DskipTests
cd ../user-service && mvn clean package -DskipTests
cd ../notification-service && mvn clean package -DskipTests

echo "All services built successfully."
