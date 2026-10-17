package com.demo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
    private static final By TITLE = By.className("title");
    private static final By ITEM_NAMES = By.className("inventory_item_name");
    private static final By CART_BADGE = By.className("shopping_cart_badge");
    private static final By CART_LINK = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return text(TITLE);
    }

    public List<String> getProductNames() {
        visible(ITEM_NAMES);
        return driver.findElements(ITEM_NAMES).stream().map(e -> e.getText()).toList();
    }

    /** Dynamic locator built from product name, e.g. "Sauce Labs Backpack" -> add-to-cart-sauce-labs-backpack. */
    public InventoryPage addToCart(String productName) {
        click(By.id("add-to-cart-" + productName.toLowerCase().replace(' ', '-')));
        return this;
    }

    public int getCartCount() {
        return Integer.parseInt(text(CART_BADGE));
    }

    public CartPage openCart() {
        click(CART_LINK);
        return new CartPage(driver);
    }
}
