# Url Shortener [![Java CI with Maven](https://github.com/morenomjc/MarkJohn-Moreno-iptiq-assignment/actions/workflows/maven.yml/badge.svg)](https://github.com/morenomjc/MarkJohn-Moreno-iptiq-assignment/actions/workflows/maven.yml)

A simple url shortener with the following features
- Supports custom SEO/random keyword to shorten URL
- Retrieves original URL from shortened URL
- Supports TTL on the shortened URL

### Background features
- Checks SEO/random keyword collisions
- Checks shortened url existence or expiration (automatically remove expired url when accessed)

## Running the tests

### Unit Tests
```shell
mvn clean test
```
