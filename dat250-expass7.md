# DAT250 – Experiment Assignment 7 (Poll App)

**Repo:** https://github.com/SeverinJo/poll-app  
**Report:** this file (`dat250-expass7.md`)

## What I did
For this exercise I built an image of the application I've built so far in this course, which also includes images my app depends on.

## How to test
docker compose up --build -d
Run messaging.http

##  Problems & how I solved them

# What to containerise
Having focused on getting the new technology to work in each weekly iteration, at the expense of the functionality of the previous iterations, I wasn't sure exactly what to set up as an image. I chose to focus on the last working iteration which tests messaging with RabbitMQ, which is what is submitted now.

# Docker connection
I had hardcoded host and port variables in application and in RabbitConfig, so docker couldn't connect properly to the other containers running in this setup. Eventually set up environment variables in docker compose and in the application which got it to work.

# Tools
For solving this I relied on code examples from lectures, online documentation and AI tools.
