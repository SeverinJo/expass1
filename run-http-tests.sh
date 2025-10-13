#!/usr/bin/env bash
set -euo pipefail

APP_PID=""

# If server already running, reuse; otherwise start it
if curl -sf http://127.0.0.1:8080/ping >/dev/null || \
   curl -sf http://127.0.0.1:8080/actuator/health >/dev/null; then
  echo "Reusing existing server on 8080"
else
  echo "Starting Spring Boot on 8080"
  ./gradlew bootRun --no-daemon & APP_PID=$!

  # Wait up to 90s for readiness
  for i in {1..90}; do
    if curl -sf http://127.0.0.1:8080/ping >/dev/null || \
       curl -sf http://127.0.0.1:8080/actuator/health >/dev/null; then
      break
    fi
    if [[ -n "$APP_PID" ]] && ! ps -p "$APP_PID" >/dev/null; then
      echo "Spring Boot exited early; see logs"
      exit 1
    fi
    sleep 1
  done
fi

echo "Application is up. (PID: $APP_PID)"

# Optionally: just wait or run some simple “messaging smoke test” with curl
# E.g., curl to /test/vote with dummy values
# curl -X POST "http://localhost:8080/test/vote?pollId=abc&optionId=opt1"

# Stop server if we started it
if [[ -n "$APP_PID" ]]; then
  kill "$APP_PID" || true
fi
