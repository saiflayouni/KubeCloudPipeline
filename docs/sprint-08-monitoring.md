# Sprint 08: Monitoring & Observability

## Goal
Implement a centralized monitoring stack using Prometheus and Grafana to visualize metrics from the microservices.

## Architecture
*   **Prometheus**: Scrapes `/q/metrics` from Task, User, and Notification services.
*   **Grafana**: Visualizes metrics (CPU, Memory, Request Count).
*   **Namespace**: `monitoring`

## Implementation Steps
1.  **Dependencies**: Added `quarkus-smallrye-metrics` to all `pom.xml` files.
2.  **Infrastructure**:
    *   Created `k8s/monitoring/prometheus-config.yaml` (Scrape targets).
    *   Created `k8s/monitoring/prometheus-deployment.yaml`.
    *   Created `k8s/monitoring/grafana-deployment.yaml`.
3.  **Deployment**:
    ```bash
    kubectl create namespace monitoring
    kubectl apply -f k8s/monitoring/
    ```

## Verification
1.  **Prometheus**: Access via NodePort on port 9090.
2.  **Grafana**: Access via LoadBalancer IP on port 80.
    *   Default Login: `admin` / `admin`
    *   Add Data Source: `http://prometheus:9090`
