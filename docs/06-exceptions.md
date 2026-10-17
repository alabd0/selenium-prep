# 06 - Exceptions & Common Errors (with Fixes)

| Exception | Cause | Fix |
|---|---|---|
| `NoSuchElementException` | Wrong locator, not loaded yet, in iframe, in other window, shadow DOM | Verify locator, explicit wait, switch to frame/window |
| `StaleElementReferenceException` | Element removed/re-rendered after you located it (DOM refresh, navigation) | Re-locate element, `ExpectedConditions.refreshed(...)`, retry, avoid caching WebElements (no `@CacheLookup` on dynamic) |
| `ElementNotInteractableException` | Element hidden, zero size, disabled, covered | Wait for clickable/visible, scroll into view, JS click |
| `ElementClickInterceptedException` | Another element (overlay, sticky header, loader, cookie banner) covers it | Wait for overlay invisibility, scroll to center, Actions, JS click |
| `TimeoutException` | Wait condition not met in time | Check locator/condition, increase timeout, check page load |
| `NoSuchFrameException` / `NoSuchWindowException` / `NoAlertPresentException` | Wrong switch target | Wait for frame/alert/window count |
| `NoSuchSessionException` / `SessionNotCreatedException` | After `quit()`, or driver/browser version mismatch | Use Selenium Manager / match versions; don't reuse driver after quit |
| `WebDriverException` (unknown error: cannot find Chrome binary, DevToolsActivePort) | Browser/driver issue, Linux/CI sandbox | `--no-sandbox`, `--disable-dev-shm-usage`, correct binary |
| `InvalidSelectorException` | Bad XPath/CSS syntax | Fix expression |
| `InvalidElementStateException` | e.g., `clear()` on non-editable | Use correct element / JS |
| `MoveTargetOutOfBoundsException` | Actions move outside viewport | Scroll first |
| `UnhandledAlertException` | Unexpected alert open | Handle alert; set `UnexpectedAlertBehaviour` |
| `JavascriptException` | JS script error | Fix script |
| `InvalidArgumentException` | Bad URL (no protocol) / bad arg | Use `https://` |
| `UnreachableBrowserException` / `ConnectException` | Grid/driver unreachable | Check hub URL/server |
| `ScreenshotException`, `NoSuchCookieException`, `NoSuchShadowRootException`, `InsecureCertificateException`, `ElementNotSelectableException` | Self-explanatory | |

## Exception hierarchy
`Throwable` -> `Exception` -> `RuntimeException` -> `WebDriverException` -> `NoSuchElementException`, `StaleElementReferenceException`, `InvalidElementStateException` -> `ElementNotInteractableException`... Selenium exceptions are unchecked.
`TimeoutException` extends `WebDriverException`.

## Debugging tips
- Reproduce in DevTools console with `$x()`.
- Take screenshot and log page source on failure (TestNG `ITestListener.onTestFailure`).
- Check frames/shadow DOM/new tab/ timing.
- Retrying flaky tests: TestNG `IRetryAnalyzer`; but fix root cause first.
- Handling stale: 
```java
for (int i=0;i<3;i++){ try { driver.findElement(by).click(); break; } catch (StaleElementReferenceException e) {} }
```
- **Driver / version mismatch** -> upgrade to Selenium >= 4.6 (Selenium Manager).
- Difference: `NoSuchElement` (never found) vs `Stale` (was found, now detached) vs `NotInteractable` (found, can't act) vs `ClickIntercepted` (found, covered).
