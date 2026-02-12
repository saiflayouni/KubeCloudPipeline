# Sprint-04: Cloud Build & Automation for task-service

## 1. Overview
This document details the configuration and automation of the CI/CD pipeline for the `task-service` using Google Cloud Build. The pipeline is designed to support a GitFlow-like workflow with distinct behaviors for `feature`, `dev`, and `main` branches.

## 2. Cloud Build Configuration
The pipeline is defined in `cloudbuild.yaml` located in the repository root.

**Pipeline Steps:**
1.  **Build & Test**: Compiles the Java/Quarkus application using Maven.
2.  **Containerize**: Builds a Docker image using the `task-service/Dockerfile`.
3.  **Publish**: Pushes the image to Google Artifact Registry:
    *   `us-central1-docker.pkg.dev/infra-earth-367100/kube-repo/task-service:$SHORT_SHA` (Traceability)
    *   `us-central1-docker.pkg.dev/infra-earth-367100/kube-repo/task-service:latest` (Convenience)
4.  **Deploy (CD)**:
    *   **Dev Branch**: Deploys to `dev` namespace in GKE.
    *   **Main Branch**: Deploys to `prod` namespace in GKE.
    *   **Feature Branches**: Skips deployment (CI only).

## 3. GitHub Integration & Triggers

### 3.1 Repository Connection
The GitHub repository `saiflayouni/KubeCloudPipeline` is connected to Cloud Build in the `us-central1` region.

### 3.2 Trigger Configuration
A single comprehensive trigger handles all relevant branches.

| Setting | Value |
| :--- | :--- |
| **Name** | `deploy-multibranch-v1` |
| **Event** | Push to a branch |
| **Source** | `^main$|^dev$|^feature/.*$` |
| **Configuration** | Autodetect `cloudbuild.yaml` |

**Setup Instructions (Console):**
Since CLI (gcloud) authentication for GitHub connections can be complex, using the Console is recommended:

1.  Navigate to **Cloud Build > Triggers** in the Google Cloud Console.
2.  Locate the existing trigger `deploy-dev-v1` (or click **Create Trigger**).
3.  **Edit** the trigger:
    *   **Name**: Rename to `deploy-multibranch-v1`.
    *   **Event**: Select "Push to a branch".
    *   **Source**: Select the repository `saiflayouni/KubeCloudPipeline`.
    *   **Branch Regex**: Enter `^main$|^dev$|^feature/.*$`.
    *   **Included Files Filter** (Optional): `task-service/**`, `cloudbuild.yaml` (Prevent builds on unrelated folder changes).
4.  Click **Save**.

## 4. Docker Automation & Traceability
*   **Traceability**: Every image tag includes the git short SHA (`$SHORT_SHA`), ensuring that every container running in the cluster can be traced back to a specific code commit.
*   **Registry**: Images are stored in `us-central1-docker.pkg.dev/infra-earth-367100/kube-repo`.

## 5. Verification Steps

### 5.1 Triggering the Pipeline
To verify the `feature/*` branch logic (CI only):

```bash
# Create and switch to a feature branch
git checkout -b feature/doc-update

# Make a change
echo "Pipeline test" >> verification.txt

# Push to trigger Cloud Build
git add verification.txt
git commit -m "test(ci): verify feature branch pipeline"
git push origin feature/doc-update
```

### 5.2 Verifying Deployment
To verify the `dev` branch deployment:
1.  Go to Cloud Build History.
2.  Ensure build status is **Success**.
3.  Check GKE:
    ```bash
    kubectl get pods -n dev
    kubectl get svc task-service -n dev
    ```

## 6. Future Improvements
*   **Microservice Isolation**: Update triggers to use `includedFiles` filter so that changes to `user-service` do not trigger `task-service` builds.
*   **GitOps**: Consider moving Kubernetes manifest application to a separate CD tool like ArgoCD for production.
