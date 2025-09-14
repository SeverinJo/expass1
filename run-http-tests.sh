#!/usr/bin/env bash
set -euo pipefail

APP_PID=""

# 1) Reuse an existing server on 8080 if it's already up
if curl -sf http://127.0.0.1:8080/ping >/dev/null || \
   curl -sf http://127.0.0.1:8080/actuator/health >/dev/null; then
  echo "Reusing existing server on 8080"
else
  echo "Starting Spring Boot on 8080"
  ./gradlew bootRun --no-daemon & APP_PID=$!

  # 2) Wait up to 90s for readiness
  for i in {1..90}; do
    if curl -sf http://127.0.0.1:8080/ping >/dev/null || \
       curl -sf http://127.0.0.1:8080/actuator/health >/dev/null; then
      break
    fi
    # fail early if the app died
    if [[ -n "$APP_PID" ]] && ! ps -p "$APP_PID" >/dev/null; then
      echo "Spring Boot exited early; see build logs"; exit 1
    fi
    sleep 1
  done
fi

# 3) Download and run JetBrains HTTP Client CLI
curl -fL -o ijhttp.zip "https://jb.gg/ijhttp/latest"
unzip -qo ijhttp.zip
./ijhttp/ijhttp scratch.http --env-file http-client.env.json --env local --report

# 4) Stop only if we started it
if [[ -n "$APP_PID" ]]; then kill "$APP_PID" || true; fi
