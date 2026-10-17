# 07 - Test Frameworks, Design Patterns & Framework Design

## TestNG annotations (execution order)
`@BeforeSuite` > `@BeforeTest` > `@BeforeClass` > `@BeforeMethod` > `@Test` > `@AfterMethod` > `@AfterClass` > `@AfterTest` > `@AfterSuite`. Also `@BeforeGroups/@AfterGroups`.

## TestNG features
- `@Test(priority=1, enabled=false, groups="smoke", dependsOnMethods="login", invocationCount=3, timeOut=2000, expectedExceptions=X.class, description="..")`
- `testng.xml`: suite/test/classes/packages/groups, `parallel="tests|classes|methods"`, `thread-count`, parameters.
- `@Parameters` (from xml) vs `@DataProvider` (data-driven, `parallel=true`), `@Optional`.
- Assertions: Hard `Assert.assertEquals` stops test; Soft `SoftAssert` collects, call `assertAll()`.
- Listeners: `ITestListener`, `ISuiteListener`, `IRetryAnalyzer`, `IAnnotationTransformer`, `IReporter`.
- Default order: alphabetical when no priority. Same priority -> alphabetical.
- Reports: default HTML, ExtentReports, Allure.
- `@Factory`, `@Listeners`, `dependsOnGroups`, `alwaysRun=true`.
- `assertEquals(actual, expected)` argument order (TestNG: actual first; JUnit: expected first).

## JUnit 5
`@Test`, `@BeforeEach/@AfterEach`, `@BeforeAll/@AfterAll` (static), `@ParameterizedTest` + `@ValueSource/@CsvSource/@MethodSource`, `@Disabled`, `@Tag`, `@Nested`, `@DisplayName`, `assertAll`, `assertThrows`. JUnit 4: `@Before/@After/@BeforeClass/@AfterClass/@Ignore/@Rule`.

## TestNG vs JUnit
TestNG: groups, dependencies, built-in parallel, DataProvider, XML config, reports. JUnit5: modular, extensions, lambdas, parameterized.

## Page Object Model (POM)
```java
public class LoginPage {
  private final WebDriver driver;
  @FindBy(id="user") private WebElement user;
  public LoginPage(WebDriver d){ this.driver=d; PageFactory.initElements(d,this); }
  public HomePage login(String u,String p){ user.sendKeys(u); ...; return new HomePage(driver); }
}
```
Benefits: reusability, maintainability, separation of locators and tests. Keep assertions in tests, not pages. **Page Factory** = `@FindBy` + `initElements` (lazy proxy; `@CacheLookup` can cause stale).

## Other design patterns
Singleton (driver manager), Factory (browser factory), Builder (test data), Strategy, Fluent interface (method chaining), Facade, Decorator (`WebDriverListener`).

## ThreadLocal for parallel
```java
private static final ThreadLocal<WebDriver> tl = new ThreadLocal<>();
public static WebDriver get(){ return tl.get(); }
```
Prevents sharing a static driver across threads.

## Framework layout (hybrid / data-driven)
```
src/main/java: base (DriverFactory, BasePage), pages, utils (WaitUtils, ExcelUtils, ConfigReader, ScreenshotUtil), listeners
src/test/java: tests
src/test/resources: config.properties, testng.xml, testdata
pom.xml, Jenkinsfile
```
Types: Linear, Modular, Data-driven, Keyword-driven, Hybrid, BDD (Cucumber).
Components: Maven, TestNG, POM, Apache POI (Excel), Jackson/Gson (JSON), `Properties` config, Log4j2/SLF4J logging, Extent/Allure reports, Git, Jenkins CI.

## Data-driven
Excel (Apache POI), CSV, JSON, DB (JDBC), properties, TestNG DataProvider.

## BDD / Cucumber
Gherkin `Feature/Scenario/Given/When/Then/And/But`, `Background`, `Scenario Outline` + `Examples`, step definitions, hooks (`@Before/@After`), tags, runner `@CucumberOptions(features, glue, plugin, tags)`, DataTable, `PicoContainer` DI.

## Maven
Lifecycle: validate, compile, test, package, verify, install, deploy; `clean`. `pom.xml` dependencies: selenium-java, testng, webdrivermanager, poi-ooxml, extentreports, log4j. `maven-surefire-plugin` runs tests w/ suite XML. Commands: `mvn clean test`, `mvn test -Dtest=ClassName`, `-Dgroups=smoke`, `-DsuiteXmlFile=testng.xml`, `-Dbrowser=chrome`. Scopes: compile, test, provided, runtime. `.m2` local repo.
