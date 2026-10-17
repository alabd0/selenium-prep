# 03 - Locators (XPath, CSS, etc.)

## 8 locators
`id`, `name`, `className`, `tagName`, `linkText`, `partialLinkText`, `cssSelector`, `xpath`.
Priority/speed: id > name > css > xpath. `className` doesn't accept compound classes (spaces) -> use CSS `.a.b`.

## XPath
- **Absolute** `/html/body/div[1]` (brittle) vs **Relative** `//div[@id='x']`.
- Attribute: `//input[@type='text']`; multiple: `//a[@href='x' and @class='y']`, `or`.
- Text: `//button[text()='Login']`, `//*[contains(text(),'Log')]`, `//a[normalize-space()='Home']`
- `contains()`, `starts-with()`, `ends-with()` (XPath 2 only, not supported in browsers).
- Index: `(//input)[2]`, `//li[last()]`, `//li[position()<3]`.
- Axes: `parent::`, `child::`, `ancestor::`, `descendant::`, `following-sibling::`, `preceding-sibling::`, `following::`, `preceding::`.
  e.g. `//td[text()='John']/following-sibling::td/input`, `//span[text()='x']/..`
- Dynamic ids: `//input[starts-with(@id,'user_')]`.
- Case-insensitive: `translate(text(),'ABC...','abc...')`.
- `//` anywhere vs `/` direct child. `.` current node, `..` parent, `*` any, `@*` any attribute.

## CSS selectors
| Goal | Selector |
|---|---|
| id | `#id` / `tag#id` |
| class | `.cls` / `tag.cls1.cls2` |
| attribute | `[type='text']` |
| contains / starts / ends | `[id*='a']`, `[id^='a']`, `[id$='a']` |
| child / descendant | `div > p`, `div p` |
| adjacent sibling / general | `h1 + p`, `h1 ~ p` |
| nth | `li:nth-child(2)`, `li:first-child`, `li:last-child`, `li:nth-of-type(2)` |
| not | `input:not([disabled])` |
CSS can't go to parent (XPath can; `:has()` now partially) and can't match by text.

## XPath vs CSS
CSS: faster, readable, can't traverse up or match text. XPath: backward traversal, text matching, more powerful, slower, verbose.

## Relative locators (Selenium 4)
```java
import static org.openqa.selenium.support.locators.RelativeLocator.with;
driver.findElement(with(By.tagName("input")).below(By.id("email")).toLeftOf(By.id("x")));
// above, below, toLeftOf, toRightOf, near (~50px)
```

## Other locator topics
- **ByChained / ByAll**: `new ByChained(By.id("a"), By.tagName("li"))` (nested), `new ByAll(...)` (OR).
- **Dynamic elements**: build locator with `String.format("//a[text()='%s']", name)`; use contains/starts-with.
- **Shadow DOM**: `element.getShadowRoot().findElement(By.css(..))` (Selenium 4, CSS only, open mode). Closed shadow roots not accessible.
- **SVG elements**: `//*[name()='svg']`, `//*[local-name()='svg']`.
- **Stale locators / Page Factory** `@FindBy(id="x")`, `@FindBys` (AND), `@FindAll` (OR), `@CacheLookup`.
- **Finding broken links**: collect `a` tags, use `HttpURLConnection` HEAD, status >= 400 broken.
- Verify in DevTools console: `$x("//xpath")`, `$$("css")`. Tools: ChroPath, SelectorsHub.
