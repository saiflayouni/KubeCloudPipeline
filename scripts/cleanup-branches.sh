#!/bin/bash

echo "🧹 Cleaning up local stale branches..."

# Fetch latest
git fetch --all --prune

# Switch to dev to avoid deleting current
git checkout dev

# Delete merged feature branches (exclude main/dev/sprint-07)
git branch | grep "feature/" | grep -v "sprint-07" | xargs git branch -D

echo "✅ Cleanup complete. Active branches:"
git branch
