# Demo Selenium Framework (Java + TestNG + Maven)

A small, interview-ready framework against https://www.saucedemo.com.

## Structure
```
src/main/java/com/demo
  config/Config.java        # config.properties + -D overrides
  driver/DriverManager.java # ThreadLocal WebDriver, Chrome/Firefox/Edge, headless
  pages/                    # Page Object Model (BasePage with explicit waits)
  utils/ScreenshotUtil.java
src/test/java/com/demo
  tests/                    # BaseTest, LoginTest (DataProvider), CartTest
  listeners/TestListener    # screenshot on failure
src/test/resources          # config.properties, testng.xml
```

## Run
```
mvn test                                  # headless chrome
mvn test -Dheadless=false -Dbrowser=firefox
mvn test -Dtest=LoginTest -DsuiteXml=     # single class (blank suite)
```

## Best practices shown (talking points)
- Page Object Model; locators private, methods return page objects (fluent flow)
- Explicit waits only, centralised in BasePage; no `Thread.sleep`
- ThreadLocal driver for safe parallel runs (`parallel="methods"` in testng.xml)
- Config externalised, overridable from CLI/CI
- Selenium Manager handles driver binaries (no WebDriverManager needed)
- Data-driven tests via `@DataProvider`; tests are independent (fresh browser per test)
- Assertions only in tests, never in page objects
- Failure screenshots via TestNG listener
