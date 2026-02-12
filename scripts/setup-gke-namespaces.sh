#!/bin/bash

# Validate Prerequisites
echo "Checking prerequisites..."
if ! command -v gcloud &> /dev/null; then
    echo "gcloud command could not be found. Please install Google Cloud SDK."
    exit 1
fi

if ! command -v kubectl &> /dev/null; then
    echo "kubectl command could not be found. Please install kubectl."
    exit 1
fi

PROJECT_ID="infra-earth-367100"
CLUSTER_NAME="kube-cloud-cluster"
ZONE="us-central1-a"

echo "Setting project to $PROJECT_ID..."
gcloud config set project $PROJECT_ID

echo "Getting cluster credentials..."
gcloud container clusters get-credentials $CLUSTER_NAME --zone $ZONE

echo "Creating namespaces if they don't exist..."
kubectl create namespace dev --dry-run=client -o yaml | kubectl apply -f -
kubectl create namespace prod --dry-run=client -o yaml | kubectl apply -f -

echo "Validation complete. You are connected to context: $(kubectl config current-context)"
