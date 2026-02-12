# CI/CD Architecture Design: Sprint-03

**Project:** KubeCloudPipeline  
**Sprint Focus:** CI/CD Pipeline Design (Google Cloud Platform)  
**Role:** Senior DevOps Architect  
**Date:** February 12, 2026  

---

## 1. Executive Summary
This document outlines the Continuous Integration and Continuous Deployment (CI/CD) strategy for the `KubeCloudPipeline` project. The goal is to automate the software delivery process, ensuring that every commit is built, tested, and deployed reliably. We will leverage Google Cloud Platform (GCP) native tools to build a portfolio-grade, scalable pipeline.

## 2. CI/CD Concepts & Application
*   **Continuous Integration (CI):**  
    *   *Concept:* Developers frequently merge code changes into a central repository where automated builds and tests connect.
    *   *Application:* Every push to a feature branch triggers a build and unit test execution for the specific microservice (e.g., `task-service`). This prevents broken code from reaching the main codebase.
*   **Continuous Deployment (CD):**  
    *   *Concept:* Automatically releasing code changes to a production-like environment after passing CI.
    *   *Application:* Merges to `dev` or `main` trigger the creation of a Docker container image, which is then automatically deployed to our Kubernetes cluster.

## 3. Technology Stack Selection (GCP)

| Component | Tool Choice | Justification |
| :--- | :--- | :--- |
| **Source Control** | **GitHub** | Industry standard, seamless integration with GCP via Cloud Build triggers. |
| **CI/CD Orchestrator** | **Google Cloud Build** | Serverless, explicitly designed for container-based builds, scales automatically, and integrates natively with GKE and Artifact Registry. |
| **Artifact Storage** | **Artifact Registry** | The successor to Container Registry. Provides secure, private Docker image storage with vulnerability scanning capabilities. |
| **Orchestration** | **Google Kubernetes Engine (GKE)** | Managed Kubernetes service. Handles the complexity of the control plane, scaling, and upgrades, allowing us to focus on the microservices. |

## 4. Pipeline Design & Triggers

### A. Branching Strategy & Triggers

1.  **Feature Branches (`feature/*`)** -> **CI Only**
    *   **Trigger:** Push to any branch matching `feature/*`.
    *   **Actions:** Compile Code -> Run Unit Tests.
    *   **Goal:** Fast feedback loop for developers. No artifacts published.

2.  **Development Branch (`dev`)** -> **CI + CD (Staging)**
    *   **Trigger:** Push/Merge to `dev`.
    *   **Actions:** Compile -> Test -> Build Docker Image -> Push to Registry (tag: `dev-commitSHA`) -> Deploy to GKE Namespace `dev`.
    *   **Goal:** Integation testing in a live environment.

3.  **Main Branch (`main`)** -> **CI + CD (Production)**
    *   **Trigger:** Push/Merge to `main`.
    *   **Actions:** Compile -> Test -> Build Docker Image -> Push to Registry (tag: `v1.0.x` or `latest`) -> Deploy to GKE Namespace `prod`.
    *   **Goal:** Stable production release.

## 5. Pipeline Stages & Responsibilities

1.  **Build Stage**
    *   *Responsibility:* Compile the Java/Quarkus application using Maven.
    *   *Output:* Compiled JAR files / Native executable.
    *   *Files:* `pom.xml`, `mvnw`.

2.  **Test Stage**
    *   *Responsibility:* Execute unit and integration tests (`mvn test`). Fail the pipeline if any test fails.
    *   *Output:* Test Pass/Fail Reports.

3.  **Package Stage**
    *   *Responsibility:* Build the Docker image using the `Dockerfile`. Tag the image with the Git commit SHA for traceability.
    *   *Output:* Docker Image stored in Artifact Registry.

4.  **Deploy Stage**
    *   *Responsibility:* Apply Kubernetes manifests (`deployment.yaml`, `service.yaml`) to the GKE cluster. Updates the running pods with the new image.
    *   *Output:* Running application in GKE.

## 6. Process Flow Diagram (ASCII)

```text
[Developer] 
    |
    +-- (git push feature/new-api) --> [GitHub]
                                          |
                                          +-- (Webhook) --> [Cloud Build: CI Job]
                                                              |
                                                              +-- 1. Maven Build
                                                              +-- 2. Maven Unit Tests
                                                              +-- 3. (Report Status to GitHub)

[Developer]
    |
    +-- (Pull Request Merge to 'dev') --> [GitHub]
                                            |
                                            +-- (Webhook) --> [Cloud Build: CD Job]
                                                                |
                                                                +-- 1. Maven Build & Test
                                                                +-- 2. Docker Build (Tag: dev-sha123)
                                                                +-- 3. Docker Push -> [Artifact Registry]
                                                                +-- 4. Set Image Tag in K8s Manifests
                                                                +-- 5. kubectl apply -> [GKE Cluster (Namespace: dev)]
```

## 7. Artifacts to be Create in Sprint-04

To implement this design, the following files will be created in the next sprint:

1.  **`cloudbuild.yaml`**: The pipeline definition file instructing Cloud Build on steps to take.
2.  **`k8s/deployment.yaml`**: Defines the Pods and Replicas for the `task-service`.
3.  **`k8s/service.yaml`**: Defines the LoadBalancer/ClusterIP to expose the service.
4.  **`Dockerfile` (Optimization)**: Refining the existing Dockerfile for production layering.

## 8. Assumptions & Constraints
*   **Initial Scope:** We will focus on the `task-service` pipeline first. Once proven, we will replicate this for `user-service` and `notification-service`.
*   **Environment:** We assume a standard GKE Standard or Autopilot cluster.
*   **Security:** Basic IAM permissions will be set up; advanced security scanning (implementation) is deferred to a later sprint.

---

### Sprint-03 Outcome Summary
We have defined a robust, scalable CI/CD architecture using Google Cloud Platform native tools. This design supports our "Main -> Dev -> Feature" git workflow and ensures isolation between development and production environments, adhering to DevOps best practices.
