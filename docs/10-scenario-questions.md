# 10 - Scenario-Based & Rapid-Fire Questions

1. **Element not found though it exists?** iframe, new window, shadow DOM, timing, dynamic locator, hidden.
2. **Click not working?** overlay/loader -> wait invisibility; scroll; JS click; Actions.
3. **Handle dynamic IDs?** contains/starts-with, relative XPath, stable attributes, axes.
4. **Handle authentication popups?** URL creds, `HasAuthentication`, CDP, AutoIt.
5. **Upload without input tag?** Robot class, AutoIt, Sikuli, or find hidden input with JS.
6. **Handle multiple tabs?** window handles, `newWindow`, switch back to parent.
7. **Run in parallel?** TestNG parallel + ThreadLocal driver or Grid.
8. **Test slow loading?** explicit waits, pageLoadTimeout, EAGER strategy.
9. **Capture screenshot on failure?** `ITestListener.onTestFailure`, attach to Extent.
10. **Verify broken links/images?** HttpURLConnection/HttpClient status codes.
11. **Handle captcha/OTP?** bypass in test env, API, mock, fixed OTP.
12. **Read Excel data?** Apache POI + DataProvider.
13. **Cross-browser?** Parameter in testng.xml + factory / Grid.
14. **Handle hidden element?** JS executor.
15. **Scroll?** JS `scrollBy`, `scrollIntoView`, Actions.scrollToElement, `Keys.PAGE_DOWN`.
16. **Handle date picker, auto-suggest, tables, sliders (Actions dragAndDropBy), drag-drop, tooltips, right-click, hover menus, iframes, popups.**
17. **Test flaky?** find root cause (sync), stable locators, retry analyzer.
18. **How do you decide a framework?** requirements, team skills, reporting, maintainability.
19. **How to run a failing subset?** `testng-failed.xml`.
20. **How to switch environments?** config.properties / `-Denv=qa` / Maven profiles.
21. **Handle pagination/web tables:** loop pages while next enabled.
22. **Check text in PDF:** PDFBox.
23. **Handle stale element in loops:** re-locate within each iteration.
24. **Headless differences:** viewport size, GPU, user-agent, downloads.

## Rapid-fire
- `driver.get` vs `navigate`? Both load; navigate adds history control.
- Can Selenium handle windows apps? No (use WinAppDriver/AutoIt).
- Is `Thread.sleep` good? No.
- `findElement` returns? `WebElement`; `findElements` -> `List<WebElement>`.
- How to get text of hidden element? `getAttribute("textContent")`/JS.
- What is `WebDriverManager`? Library to auto-download drivers (now superseded by Selenium Manager).
- Difference `getText` vs `getAttribute("value")`?  visible text vs input value.
- XPath axes? list in 03.
- Default timeouts: implicit 0, pageLoad 300s, script 30s.
- What does `driver.quit()` do to chromedriver process? Terminates it.
- Selenium Manager? Built-in binary that fetches drivers/browsers.
- How to maximize window? `manage().window().maximize()`.
- What is `RemoteWebDriver`? Class that sends commands to a remote server via HTTP.
- Headless browsers: Chrome/Firefox headless, HtmlUnitDriver.
- How do you handle sync between API and UI? Poll with FluentWait.
- Write test for login (Positive/Negative/boundary), assertion types (title, url, text, element presence).
