#!/bin/bash
set -e

echo "Starting Sprint-07 Setup..."

# 1. Create Namespace
echo "Creating request namespace: dev-sprint7..."
kubectl apply -f k8s/namespace-dev-sprint7.yaml

# 2. Apply Ingress
echo "Applying Ingress Configuration..."
kubectl apply -f k8s/ingress.yaml -n dev-sprint7

echo "Sprint-07 Environment Ready."
echo "Note: Services will be deployed by Cloud Build upon git push."
echo "      Until then, the Ingress backend will be unhealthy."
