package com.demo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private static final By ITEM_NAMES = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getItemNames() {
        visible(ITEM_NAMES);
        return driver.findElements(ITEM_NAMES).stream().map(e -> e.getText()).toList();
    }
}
