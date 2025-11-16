#!/usr/bin/env bash
set -euo pipefail

if [[ "${1:-}" == "--build" ]] || [[ ! -d "target/classes" ]]; then
  mvn -q -DskipTests package
fi

mvn -q -DskipTests exec:java


