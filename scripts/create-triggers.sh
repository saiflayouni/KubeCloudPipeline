#!/bin/bash

# Configuration
PROJECT_ID="infra-earth-367100"
REPO_NAME="KubeCloudPipeline"
REPO_OWNER="saiflayouni" 
# However, standard trigger creation via gcloud often uses --repo or --trigger-config.
# Since we don't know the exact connection name, we'll provide commands that assume a standard setup or print instructions.

echo "Creating Cloud Build Triggers..."

# Trigger for Task Service
gcloud beta builds triggers create github \
    --name="deploy-task-service" \
    --region="us-central1" \
    --repo-name="$REPO_NAME" \
    --repo-owner="$REPO_OWNER" \
    --branch-pattern="^main$|^dev$|^feature/.*$" \
    --build-config="cloudbuild-task.yaml" \
    --included-files="task-service/**,k8s/task-service/**" \
    --project="$PROJECT_ID" \
    --description="Build and deploy Task Service on changes"

# Trigger for User Service
gcloud beta builds triggers create github \
    --name="deploy-user-service" \
    --region="us-central1" \
    --repo-name="$REPO_NAME" \
    --repo-owner="$REPO_OWNER" \
    --branch-pattern="^main$|^dev$|^feature/.*$" \
    --build-config="cloudbuild-user.yaml" \
    --included-files="user-service/**,k8s/user-service/**" \
    --project="$PROJECT_ID" \
    --description="Build and deploy User Service on changes"

# Trigger for Notification Service
gcloud beta builds triggers create github \
    --name="deploy-notification-service" \
    --region="us-central1" \
    --repo-name="$REPO_NAME" \
    --repo-owner="$REPO_OWNER" \
    --branch-pattern="^main$|^dev$|^feature/.*$" \
    --build-config="cloudbuild-notification.yaml" \
    --included-files="notification-service/**,k8s/notification-service/**" \
    --project="$PROJECT_ID" \
    --description="Build and deploy Notification Service on changes"

echo "Triggers created. Verify in GCP Console."
