# 05 - Actions, Alerts, Frames, Windows, JS & Advanced

## Actions class (mouse/keyboard)
```java
Actions a = new Actions(driver);
a.moveToElement(el).perform();                    // hover
a.click(el).doubleClick(el).contextClick(el).perform();   // click, dblclick, right-click
a.clickAndHold(src).moveToElement(dst).release().perform();
a.dragAndDrop(src, dst).perform();
a.dragAndDropBy(src, 100, 0).perform();
a.keyDown(Keys.SHIFT).sendKeys("abc").keyUp(Keys.SHIFT).perform();
a.scrollToElement(el).perform();                  // Selenium 4
a.scrollByAmount(0, 500).perform();
a.moveByOffset(10,10).perform();
```
`build()` creates action; `perform()` executes (perform calls build). Forgetting `perform()` = nothing happens.

## Alerts
```java
Alert al = driver.switchTo().alert();
al.getText(); al.accept(); al.dismiss(); al.sendKeys("txt");   // prompt
```
Types: simple alert, confirm, prompt. Not locatable via DOM. `NoAlertPresentException` if absent; `UnhandledAlertException` if alert blocks. Authentication popup: `******host` or CDP/`HasAuthentication`.

## Frames / iframes
```java
driver.switchTo().frame(0 | "idOrName" | webElement);
driver.switchTo().parentFrame();
driver.switchTo().defaultContent();
```
Count frames: `driver.findElements(By.tagName("iframe")).size()`. `NoSuchFrameException` if invalid. Nested frames: switch parent then child.

## Windows / tabs
```java
String parent = driver.getWindowHandle();
for (String h : driver.getWindowHandles()) if (!h.equals(parent)) driver.switchTo().window(h);
driver.switchTo().newWindow(WindowType.TAB);
```
`getWindowHandles()` returns `Set<String>` (unordered). Handle = unique session-window id. `NoSuchWindowException` if closed/invalid.

## JavascriptExecutor
```java
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].click();", el);
js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
js.executeScript("arguments[0].scrollIntoView(true);", el);
js.executeScript("arguments[0].style.border='3px solid red'", el);
String t = (String) js.executeScript("return document.title");
js.executeScript("arguments[0].value='x'", el);
```
Use when normal click is intercepted, for scrolling, hidden elements, reading values.

## Other
- **Headless**: `--headless=new`.
- **Window size / responsive**: `driver.manage().window().setSize(new Dimension(375,812))`.
- **File upload**: `sendKeys(path)` on input type=file; else Robot class / AutoIt / Sikuli.
- **File download**: set Chrome prefs `download.default_directory`, then poll the folder (FluentWait).
- **Robot class** – `java.awt.Robot` for OS key events.
- **Browser logs/performance**: `driver.manage().logs().get(LogType.BROWSER)`; CDP network interception: `DevTools devTools = ((ChromeDriver)driver).getDevTools(); devTools.createSession();`.
- **Geolocation, emulation** via CDP `Emulation.setGeolocationOverride`.
- **SSL cert errors**: `options.setAcceptInsecureCerts(true)`.
- **Proxy**: `Proxy p = new Proxy(); p.setHttpProxy("host:port"); options.setProxy(p);`
- **Capabilities / Options** : `ChromeOptions`, `FirefoxOptions`, `MutableCapabilities`; Chrome profile `--user-data-dir`; `excludeSwitches` "enable-automation".
- **Handling calendar/datepicker**: loop next until month matches then click day; or JS set value.
- **Tables**: rows `//table//tr`, cells `td`; dynamic with index loops.
- **Auto-suggest**: type, wait for list, iterate options, click matching text.
- **Tooltips**: `getAttribute("title")` or hover with Actions.
- **Captcha / OTP**: ask devs to disable in test env, use API/test keys, mock.
- **Browser notifications / permissions**: `--disable-notifications`, prefs.
- **WebDriverListener (Selenium 4) / EventFiringDecorator** (replacing EventFiringWebDriver) for logging events.
