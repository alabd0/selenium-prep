# 08 - Grid, CI/CD, Tools & Commands

## Selenium Grid 4
Modes: Standalone, Hub-Node, Fully Distributed (Router, Distributor, Session Map, Session Queue, Event Bus, Nodes).
```bash
java -jar selenium-server-4.x.jar standalone
java -jar selenium-server-4.x.jar hub
java -jar selenium-server-4.x.jar node --hub http://hub:4444
```
UI at `http://localhost:4444`. Remote test:
```java
WebDriver d = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());
```
Use: parallel, cross-browser, cross-OS. Grid 3 used `DesiredCapabilities`; Grid 4 uses Options.

## Docker
`docker run -d -p 4444:4444 --shm-size=2g selenium/standalone-chrome`; docker-compose with hub + chrome/firefox nodes. Cloud: BrowserStack, Sauce Labs, LambdaTest.

## CI/CD with Jenkins
Pipeline: checkout (Git) -> `mvn clean test` -> publish reports (JUnit/Allure/Extent) -> email. Trigger: cron (`H 2 * * *`), SCM poll, webhook. Parameterized builds for browser/env. Alternatives: GitHub Actions, GitLab CI, Azure DevOps.
```yaml
- uses: actions/setup-java@v4
  with: { java-version: 17, distribution: temurin }
- run: mvn -B clean test -Dheadless=true
```

## Git commands
`clone, status, add, commit, push, pull, fetch, branch, checkout -b, merge, rebase, stash, log, diff, reset, revert, cherry-pick, tag`. `git pull` = fetch + merge.

## Tools
Maven/Gradle, Git, Jenkins, Docker, TestNG/JUnit, Cucumber, Extent/Allure, Log4j, Apache POI, WebDriverManager, REST Assured (API), Postman, JIRA (bugs), Sikuli/AutoIt/Robot (OS), AShot (visual), BrowserStack, SonarQube, SelectorsHub/ChroPath, Appium (mobile), JMeter (performance).

## Handy CLI
`mvn -v`, `java -version`, `chromedriver --version`, `mvn dependency:tree`, `mvn clean install -DskipTests`.

## Logging
Log4j2: levels TRACE<DEBUG<INFO<WARN<ERROR<FATAL; config `log4j2.xml`; `Logger log = LogManager.getLogger(X.class)`.

## Reporting
Extent: `ExtentReports`, `ExtentSparkReporter`, `ExtentTest.log(Status.PASS,..)`, attach screenshot on failure, `flush()`.

## Automation strategy
Test pyramid (unit > API > UI), what to automate (regression, smoke, repetitive, data heavy) and not (exploratory, one-off, unstable UI, usability). Flaky tests: sync issues, test data, env, shared state -> fix, isolate, retry sparingly. Locator strategy: stable attributes, `data-testid`.
