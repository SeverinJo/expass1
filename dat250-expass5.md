# DAT250 – Experiment Assignment 5 (Poll App)

**Repo:** https://github.com/SeverinJo/poll-app  
**Report:** this file (`dat250-expass5.md`)

## What I did
Converted the JPA Hibernate application to use Redis(Valkey) document database.

##  Problems & how I solved them

# Valkey
Installing valkey and running it in a container on my Arch system, required some tinkering before I got it to work. Getting the redis equivalents in valkey right, was a bit confusing at first.

# Misunderstanding
I misunderstood the part of the task were we were supposed to try out the redis cli and do the use cases, which I thought were supposed to be done in Java. I think I got it right in the end, doing a short script creating a table and adding users like the use cases.

# Misunderstanding part 2
I was also really confused by the caching task. I made a local cache. The Spring Redis Cache integration which I thought was what was meant was optional and a bit more troublesome.

# Tools
For solving this I relied on code examples from lectures, online documentation and AI tools.
