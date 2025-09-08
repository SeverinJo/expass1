# run-http-tests.sh
set -euo pipefail
./gradlew bootRun --no-daemon & APP_PID=$!
until curl -sf http://127.0.0.1:8080/ping >/dev/null; do sleep 1; done

curl -fL -o ijhttp.zip "https://jb.gg/ijhttp/latest"
unzip -qo ijhttp.zip
./ijhttp --env-file http-client.env.json --env local --report pollapp.http
kill $APP_PID
