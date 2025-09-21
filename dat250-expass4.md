# DAT250 – Experiment Assignment 4 (Poll App)

**Repo:** https://github.com/SeverinJo/poll-app  
**Report:** this file (`dat250-expass4.md`)

## What I built
- An app where users can create accounts and polls, as well as vote on created polls, but now using the Hibernate and JPA framework.

## How to test
```bash
./gradlew test
```

## Technical problems & how I solved them

# Entity Model
Getting all the annotations right, and the relationships between the entities. Especially annotations like mappedBy, which is still a bit confusing, proved a challenge to get right.

# Over-complication
Misunderstood the scope of the task, so I went along and created repositories and services to do crud operations on the H2 database. Turns out it was just changing the domain model to fit the Hibernate framework.

# Tools
For solving this I relied on videos from lectures, online documentation and AI tools.

# Database Screenshots
<img alt="250921_12h39m23s_screenshot.png" src="DB%20Screenshots/250921_12h39m23s_screenshot.png"/>
<img alt="250921_12h39m43s_screenshot.png" src="DB%20Screenshots/250921_12h39m43s_screenshot.png"/>
<img alt="250921_12h39m55s_screenshot.png" src="DB%20Screenshots/250921_12h39m55s_screenshot.png"/>
<img alt="250921_12h40m06s_screenshot.png" src="DB%20Screenshots/250921_12h40m06s_screenshot.png"/>
<img alt="250921_12h40m50s_screenshot.png" src="DB%20Screenshots/250921_12h40m50s_screenshot.png"/>