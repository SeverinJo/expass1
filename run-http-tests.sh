# run-http-tests.sh
set -euo pipefail

./gradlew bootRun --no-daemon & APP_PID=$!

# 60s timeout
for i in {1..60}; do
  if curl -sf http://127.0.0.1:8080/ping >/dev/null; then break; fi
  sleep 1
done

# Grab CLI and run
curl -fL -o ijhttp.zip "https://jb.gg/ijhttp/latest"
unzip -qo ijhttp.zip
./ijhttp/ijhttp --env-file http-client.env.json --env local --report pollapp.http

kill $APP_PID
