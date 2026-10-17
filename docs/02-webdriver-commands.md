# 02 - WebDriver & WebElement Commands

## Browser (WebDriver) commands
| Command | Purpose |
|---|---|
| `get(url)` / `navigate().to(url)` | Open URL. `get` waits for page load; `navigate` same but history-aware |
| `navigate().back()/forward()/refresh()` | History navigation |
| `getTitle()`, `getCurrentUrl()`, `getPageSource()` | Page info |
| `getWindowHandle()` / `getWindowHandles()` | Current id / Set of all ids |
| `close()` | Closes current window only |
| `quit()` | Closes all windows, ends session (always use in teardown) |
| `manage().window().maximize()/minimize()/fullscreen()/setSize()/setPosition()` | Window |
| `manage().getCookies()/addCookie()/deleteCookieNamed()/deleteAllCookies()` | Cookies |
| `manage().timeouts()` | pageLoad / implicit / script timeouts |
| `switchTo()` | frame, window, alert, activeElement, defaultContent, parentFrame |

**get() vs navigate().to()**: functionally the same; `navigate()` exposes back/forward/refresh and accepts `URL` object. **close() vs quit()**: window vs session.

## WebElement commands
`click()`, `sendKeys()`, `clear()`, `submit()`, `getText()`, `getAttribute()` (attr/property), `getDomAttribute()`, `getDomProperty()`, `getCssValue()`, `getTagName()`, `isDisplayed()`, `isEnabled()`, `isSelected()`, `getLocation()`, `getSize()`, `getRect()`, `getScreenshotAs()`, `findElement(s)`, `getShadowRoot()`, `getAccessibleName()`, `getAriaRole()`.

- `getText()` returns **visible** text only; `getAttribute("value")` for input values; `getAttribute("textContent")` for hidden text.
- `sendKeys` vs `clear` – `clear()` fails on some custom inputs; fallback: `sendKeys(Keys.chord(Keys.CONTROL,"a"), Keys.DELETE)`.
- `isDisplayed()` returns false for hidden; throws `NoSuchElementException` if missing from DOM (use `findElements().size()>0` for existence).
- `click()` vs JS click: JS click bypasses overlay/visibility checks (use as last resort).
- `submit()` only works inside a `<form>`; deprecated style – prefer click on button.

## findElement vs findElements
- `findElement` -> first match, throws `NoSuchElementException` if none.
- `findElements` -> `List<WebElement>`, empty list if none (no exception).

## Keys & typing
```java
el.sendKeys("text", Keys.ENTER);
el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
fileInput.sendKeys("/abs/path/file.pdf");   // upload
```
## Screenshots
```java
File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(src, new File("shot.png"));
element.getScreenshotAs(OutputType.FILE);  // element screenshot (Selenium 4)
```
Full-page screenshot: Firefox `((FirefoxDriver)driver).getFullPageScreenshotAs(...)`, Chrome via CDP, or AShot.

## Cookies
```java
driver.manage().addCookie(new Cookie("k","v"));
Cookie c = driver.manage().getCookieNamed("k");
driver.manage().deleteAllCookies();
```
Use cookies to skip login (save after login, inject in later tests; navigate to domain first).

## Select (dropdowns)
```java
Select s = new Select(driver.findElement(By.id("dd")));
s.selectByVisibleText("A"); s.selectByValue("1"); s.selectByIndex(0);
s.getOptions(); s.getFirstSelectedOption(); s.getAllSelectedOptions(); s.isMultiple();
s.deselectAll(); // multi-select only
```
`Select` works only with `<select>`. Custom (div/ul) dropdowns: click and then click the option locator.
