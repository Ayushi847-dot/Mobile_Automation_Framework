package com.example.tests;

import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.ProductPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ShopEasyTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ProductPage productPage;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Initialize Extent Reports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("ShopEasy Tests");

        // Set Desired Capabilities for Android
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "YourDeviceName");
        capabilities.setCapability("appPackage", "com.example.shopeasy");
        capabilities.setCapability("appActivity", "com.example.shopeasy.MainActivity");

        // Initialize WebDriver
        driver = new AppiumDriver<>(new URL("https://www.hndmd.com/login/"), capabilities);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void testValidLogin() {
        test.info("Starting valid login test");
        try {
            loginPage.login("validUser", "validPass");
            boolean isHomeDisplayed = driver.findElement(By.id("homeScreen")).isDisplayed();
            Assert.assertTrue(isHomeDisplayed);
            test.pass("Valid login test passed");
        } catch (Exception e) {
            test.fail("Valid login test failed: " + e.getMessage());
        }
    }

    @Test
    public void testNavigationToProfile() {
        test.info("Starting navigation to profile test");
        try {
            homePage.navigateToProfile();
            boolean isProfileDisplayed = driver.findElement(By.id("profileScreen")).isDisplayed();
            Assert.assertTrue(isProfileDisplayed);
            test.pass("Navigation to profile test passed");
        } catch (Exception e) {
            test.fail("Navigation to profile test failed: " + e.getMessage());
        }
    }

    @Test
    public void testAddToCart() {
        test.info("Starting add to cart test");
        try {
            homePage.selectProduct();
            productPage.addToCart();
            boolean isItemAdded = driver.findElement(By.id("cartItem")).isDisplayed();
            Assert.assertTrue(isItemAdded);
            test.pass("Add to cart test passed");
        } catch (Exception e) {
            test.fail("Add to cart test failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
}