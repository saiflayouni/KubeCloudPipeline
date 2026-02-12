# Sprint 05: GKE Environment Setup

## 🎯 Goal
Provision the Google Kubernetes Engine (GKE) cluster and configure `kubectl` access.

## ✅ Delivered
- [x] GKE Cluster `kube-cloud-cluster` provisioned in `us-central1-a`.
- [x] `kubectl` configured locally.
- [x] Namespaces created: `dev`, `prod`.
- [x] Cloud Build service account permissions (GKE Developer, Storage Admin).

## 🏗 Infrastructure
- **Provider**: Google Cloud Platform
- **Service**: GKE Standard
- **Nodes**: 3 (Autoscaling enabled)
