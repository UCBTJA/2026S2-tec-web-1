# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Spring Boot 4.1.1 web application (Java 25 toolchain), built with Gradle. Group `ucb.tecweb1`, root project name `demo`. Runs on port `8090` (configured in `src/main/resources/application.yaml`).

## Commands

- Run the app: `./gradlew bootRun`
- Build: `./gradlew build`
- Run all tests: `./gradlew test`
- Run a single test class: `./gradlew test --tests "ucb.tecweb1.demo.DemoApplicationTests"`
- Clean build: `./gradlew clean build`

Tests use JUnit 5 (JUnit Platform), configured via `useJUnitPlatform()` in `build.gradle`.

## Architecture

- `DemoApplication.java` — standard `@SpringBootApplication` entry point.
- `SiteController.java` — single `@RestController` handling all HTTP routes (`/`, `/contact`, `/greeting`). Currently returns raw HTML strings directly from controller methods rather than using templates or serving from `static/`.
- `src/main/resources/static/` — static HTML files (`about.html`, `form.html`) served directly by Spring Boot's default static resource handling; these are not currently linked from the controller.
- `runtimeOnly 'org.postgresql:postgresql'` is declared as a dependency, but no datasource/JPA configuration exists yet in `application.yaml` — the app does not currently connect to a database.
