# 09 - Core Java for Automation Interviews

## OOP
- **Encapsulation** (private fields + getters; POM locators private), **Inheritance** (BaseTest/BasePage), **Polymorphism** (overloading = compile time; overriding = runtime; `WebDriver d = new ChromeDriver()`), **Abstraction** (interfaces/abstract classes; WebDriver interface).
- Abstract class vs interface; `this`, `super`; constructors; static vs instance; `final`, `finally`, `finalize`; access modifiers; method overloading vs overriding.

## Collections
`List` (ArrayList, LinkedList), `Set` (HashSet, LinkedHashSet, TreeSet), `Map` (HashMap, LinkedHashMap, TreeMap, Hashtable), `Queue`. ArrayList vs LinkedList; HashMap vs Hashtable (sync, null keys); HashSet no duplicates. `getWindowHandles` -> Set; `findElements` -> List. Iterator, `Collections.sort`, `Comparable` vs `Comparator`.

## Strings
Immutable; String pool; `==` vs `equals`; StringBuilder (not sync) vs StringBuffer (sync); `substring, split, trim, replace, contains, equalsIgnoreCase, charAt, indexOf, toCharArray`.

## Exceptions
checked vs unchecked; try/catch/finally; `throw` vs `throws`; custom exceptions; try-with-resources; multi-catch.

## Java 8+
Lambdas, Streams (`filter, map, collect, sorted, distinct, count`), Optional, functional interfaces, method refs.
```java
List<String> texts = driver.findElements(By.cssSelector("li")).stream().map(WebElement::getText).collect(Collectors.toList());
```
## Common coding questions
Reverse string, palindrome, anagram, count characters/duplicates/vowels, fibonacci, factorial, prime, swap numbers, sort array, find max/second max, remove duplicates from array/list, count words, find missing number, character frequency with HashMap, first non-repeating char, string rotation, check balanced brackets, Armstrong number, array merge, 2D arrays.
```java
String rev = new StringBuilder(s).reverse().toString();
Map<Character,Integer> m = new HashMap<>(); for(char c: s.toCharArray()) m.merge(c,1,Integer::sum);
```
## Other
Wrapper classes, autoboxing, `static` blocks, `Singleton`, `equals/hashCode`, generics, threads (Runnable/Thread, synchronized, ExecutorService), `ThreadLocal`, File I/O, `Properties`, Date/`LocalDate`, regex, JDBC (`Connection, Statement, ResultSet`), garbage collection, JVM/JRE/JDK, `var`, records.
