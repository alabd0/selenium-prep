# 01 - Selenium Basics & Architecture

## Q: What is Selenium? Components?
- **Selenium WebDriver** – API to automate browsers. **Selenium IDE** – record/playback. **Selenium Grid** – parallel/remote execution. (Selenium RC is deprecated.)
- Supports Java, Python, C#, JS, Ruby, Kotlin. Web apps only (no desktop/mobile native – use Appium).

## Q: Selenium 3 vs Selenium 4
| Selenium 3 | Selenium 4 |
|---|---|
| JSON Wire Protocol (+W3C) | Fully W3C WebDriver protocol |
| No relative locators | Relative locators (`RelativeLocator.with`) |
| New window via JS hacks | `driver.switchTo().newWindow(WindowType.TAB/WINDOW)` |
| Old Grid (hub/node) | New Grid (standalone, hub-node, distributed), Docker, GraphQL, observability |
| Manual drivers | Selenium Manager (auto-downloads drivers, 4.6+) |
| – | Chrome DevTools Protocol (CDP) & BiDi, element screenshots, `getRect()` |

## Q: WebDriver architecture
Test script (client bindings) -> HTTP (W3C) -> Browser driver (chromedriver/geckodriver/msedgedriver) -> Browser.

## Q: Class hierarchy
`SearchContext` -> `WebDriver` -> `RemoteWebDriver` -> `ChromeDriver/FirefoxDriver/EdgeDriver/SafariDriver`.
`WebDriver` is an **interface**; `WebElement` extends `SearchContext, TakesScreenshot`.
Why `WebDriver driver = new ChromeDriver();`? Programming to the interface; swap browsers easily.

## Q: Launching browsers
```java
WebDriver driver = new ChromeDriver();            // Selenium Manager resolves driver (4.6+)
ChromeOptions o = new ChromeOptions();
o.addArguments("--headless=new","--start-maximized","--incognito","--disable-notifications","--window-size=1920,1080");
WebDriver d = new ChromeDriver(o);
```
Older: `System.setProperty("webdriver.chrome.driver","path")` or WebDriverManager (`WebDriverManager.chromedriver().setup()`).

## Q: Selenium limitations
No captcha/OTP, barcode, desktop apps, image comparison (needs Sikuli/AShot), file upload dialogs (OS-level) except `sendKeys` on `input[type=file]`, no built-in reporting.

## Q: Selenium vs Cypress/Playwright/QTP(UFT)
Selenium: multi-language, multi-browser, open source, out-of-process. Cypress: JS only, runs in browser, limited multi-tab. Playwright: modern auto-wait, multi-lang. UFT: paid, VBScript.
