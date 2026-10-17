package com.demo.tests;

import com.demo.config.Config;
import com.demo.pages.InventoryPage;
import com.demo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Valid user can log in")
    public void validLogin() {
        InventoryPage inventory = new LoginPage(driver).open()
                .loginAs(Config.get("username"), Config.get("password"));
        Assert.assertEquals(inventory.getTitle(), "Products");
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
            {"locked_out_user", "secret_sauce", "Sorry, this user has been locked out."},
            {"standard_user", "wrong", "Username and password do not match"},
            {"", "", "Username is required"},
        };
    }

    @Test(dataProvider = "invalidCredentials", description = "Invalid logins show an error")
    public void invalidLogin(String user, String pass, String expectedError) {
        String error = new LoginPage(driver).open().loginExpectingFailure(user, pass).getError();
        Assert.assertTrue(error.contains(expectedError), "Actual error: " + error);
    }
}
