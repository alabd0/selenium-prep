package com.demo.tests;

import com.demo.config.Config;
import com.demo.pages.CartPage;
import com.demo.pages.InventoryPage;
import com.demo.pages.LoginPage;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(description = "Added products appear in the cart")
    public void addProductsToCart() {
        InventoryPage inventory = new LoginPage(driver).open()
                .loginAs(Config.get("username"), Config.get("password"));
        inventory.addToCart("Sauce Labs Backpack").addToCart("Sauce Labs Bike Light");
        Assert.assertEquals(inventory.getCartCount(), 2);

        CartPage cart = inventory.openCart();
        Assert.assertEquals(cart.getItemNames(), List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"));
    }
}
