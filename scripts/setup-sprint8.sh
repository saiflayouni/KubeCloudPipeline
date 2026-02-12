#!/bin/bash
set -e

echo "Starting Sprint-08 Monitoring Setup..."

# 1. Create Namespace
echo "Creating namespace: dev-monitoring..."
kubectl create namespace dev-monitoring --dry-run=client -o yaml | kubectl apply -f -

echo "Creating namespace: monitoring..."
kubectl create namespace monitoring --dry-run=client -o yaml | kubectl apply -f -

# 2. Apply Monitoring Stack
echo "Deploying Prometheus & Grafana..."
kubectl apply -f k8s/monitoring/

# 3. Apply Ingress (Using same Ingress but in new namespace)
# Note: Ingress needs valid service names in the namespace.
# We will just copy the ingress from ingress.yaml but verify names.
# For now, we rely on NodePort/LoadBalancer for Monitoring tools access.

echo "Sprint-08 Monitoring Environment Ready."
echo "Prometheus: NodePort 30090 (Need to find assigned port)"
echo "Grafana: LoadBalancer IP (Pending)"
