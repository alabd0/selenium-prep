package com.demo.listeners;

import com.demo.driver.DriverManager;
import com.demo.utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Takes a screenshot on failure. Runs before @AfterMethod, so the driver is still alive. */
public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (DriverManager.getDriver() != null) {
            ScreenshotUtil.capture(DriverManager.getDriver(), result.getMethod().getMethodName());
        }
    }
}
