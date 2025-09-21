# DAT250 – Experiment Assignment 3 (Poll App)

**Repo:** https://github.com/SeverinJo/poll-app  
**Report:** this file (`dat250-expass3.md`)

## What I built
- Chose Svelte to create a user interface where users can create accounts and polls, as well as vote on created polls.


## How to run
```bash
./gradlew bootRun
```
## How to test (HTTP client)

- Request file: scratch.http
- Env: http-client.env.json
- Local run:
```
./run-http-tests.sh
```

## Technical problems & how I solved them

# Frontend
Not having done anything frontend related since DAT108, this was somewhat unusual for me and took some time getting used to.

# Connection
Integrating frontend and backend was a bit of a challenge, and I chose to split responsibilities into different scripts. One for clean calls to the backend, one for mounting it and the svelte file for logic and tying it all together.

# Tools
For solving this I relied on videos from lectures, online guides and AI tools.