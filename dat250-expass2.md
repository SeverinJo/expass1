# DAT250 – Experiment Assignment 2 (Poll App)

**Repo:** <link to your GitHub repository>  
**Report:** this file (`dat250-expass2.md`)

## What I built
- Spring Boot REST API for Users, Polls, VoteOptions, Votes.
- In-memory `PollManager` (`@Component`) with HashMaps.
- Controllers: `UserController`, `PollController`, `VoteController`.
- JSON serialization handling via `@JsonIdentityInfo`.

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

# Gradle
Build and setup scripts in gradle was entirely new to me.

# Spring
Spring was a bit of a task getting back into, as it's been a while since DAT108. Annotations, serialization, dependencies and relationships between the entities in the domain were a bit especially tricky. 

# HTTP Client
Intellij's HTTP client was not like Swagger which I've used before, and took some time getting right.

# Tools
For solving this I relied on videos from lectures, online guides and AI tools.