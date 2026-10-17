package com.demo.pages;

import com.demo.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BTN = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(Config.get("baseUrl"));
        return this;
    }

    public InventoryPage loginAs(String user, String pass) {
        type(USERNAME, user);
        type(PASSWORD, pass);
        click(LOGIN_BTN);
        return new InventoryPage(driver);
    }

    public LoginPage loginExpectingFailure(String user, String pass) {
        type(USERNAME, user);
        type(PASSWORD, pass);
        click(LOGIN_BTN);
        return this;
    }

    public String getError() {
        return text(ERROR);
    }
}
