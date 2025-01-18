package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    @FindBy(id = "profileIcon")
    private WebElement profileIcon;

    @FindBy(id = "productItem")
    private WebElement productItem;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToProfile() {
        try {
            profileIcon.click();
        } catch (Exception e) {
            System.out.println("Error during navigation to profile: " + e.getMessage());
        }
    }

    public void selectProduct() {
        try {
            productItem.click();
        } catch (Exception e) {
            System.out.println("Error during product selection: " + e.getMessage());
        }
    }