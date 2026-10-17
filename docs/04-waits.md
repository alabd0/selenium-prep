# 04 - Waits & Synchronization

Why: pages load asynchronously; reading elements too early causes `NoSuchElementException`, `ElementNotInteractableException`, `StaleElementReferenceException`.

## Types
1. **Thread.sleep** – hard wait, always waits fully, avoid.
2. **Implicit wait** – global, for `findElement(s)` only; polls DOM until timeout.
   `driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));`
3. **Explicit wait** – `WebDriverWait` + `ExpectedConditions`, per condition.
4. **Fluent wait** – explicit wait with custom polling & ignored exceptions.
5. **Page load timeout** – `pageLoadTimeout(Duration)`; throws `TimeoutException`.
6. **Script timeout** – `scriptTimeout(Duration)` for `executeAsyncScript`.

## Explicit
```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("x")));
wait.until(ExpectedConditions.elementToBeClickable(By.id("b"))).click();
```
Common conditions: `presenceOfElementLocated`, `visibilityOf(...)`, `invisibilityOfElementLocated`, `elementToBeClickable`, `textToBePresentInElement`, `titleContains`, `urlContains`, `alertIsPresent`, `frameToBeAvailableAndSwitchToIt`, `numberOfWindowsToBe`, `stalenessOf`, `refreshed`, `numberOfElementsToBe`, `attributeToBe`.
Custom: `wait.until(d -> d.findElement(By.id("x")).getText().length() > 3);`
Default polling = 500 ms.

## Fluent
```java
Wait<WebDriver> w = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofSeconds(2))
    .ignoring(NoSuchElementException.class)
    .withMessage("not found");
w.until(d -> d.findElement(By.id("x")));
```
## Page ready
```java
wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
```
## Page load strategy
`options.setPageLoadStrategy(PageLoadStrategy.EAGER)` – NORMAL (default, waits `load`), EAGER (DOMContentLoaded), NONE.

## Key interview points
- **Don't mix implicit and explicit waits** – unpredictable total wait (can sum/multiply). Official docs warn.
- Implicit: applies to whole session, only find methods. Explicit: specific condition, any element.
- `presence` = in DOM (may be hidden); `visibility` = in DOM + displayed (size>0); `clickable` = visible + enabled.
- Wait for **AJAX/jQuery**: `return jQuery.active == 0`.
- `TimeoutException` thrown when condition not met.
- Selenium 4 uses `Duration`, Selenium 3 used `(driver, long seconds)` (deprecated).
