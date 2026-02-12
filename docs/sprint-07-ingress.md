# Sprint 07: Ingress & Production Scaling

## Goal
Implement a production-ready entry point for the cluster using a GKE Ingress Controller and enable horizontal scaling for all services.

## Architecture Change
*   **Before**: Services exposed via `ClusterIP` (internal only).
*   **After**: Services exposed via `NodePort` + Ingress (L7 Load Balancer).
*   **Scaling**: Replicas increased from 1 to 3 to demonstrate high availability.

## Implementation Steps
1.  **Namespace**: Created `dev-sprint7` to isolate the environment.
2.  **Deployment Updates**:
    *   Replicas: 3
    *   CPU Requests: Reduced to `15m` to fit low-cost cluster.
    *   Probes: Tuned `initialDelaySeconds` to 60s.
3.  **Ingress**:
    *   Created `k8s/ingress.yaml`
    *   Routes: `/tasks`, `/users`, `/notifications`

## Verification
```bash
kubectl get ingress -n dev-sprint7
curl http://<INGRESS_IP>/users
```
