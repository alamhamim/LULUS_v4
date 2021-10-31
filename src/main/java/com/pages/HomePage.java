package com.pages;

import com.dataProvider.HomePageDataProvider;
import com.util.SeleniumHelper;
import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HomePage {

    private WebDriver driver;
    private Properties properties;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        properties = SeleniumHelper.fileReader("src/main/resources/PageData/home_page.properties");
    }

    @FindBy(tagName = "a")
    private List<WebElement> all_a_tag;

    @FindBy(xpath = "//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li/a")
    private List<WebElement> all_header_homepage;

    @FindBy(xpath = "//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li[1]/a[1]")
    WebElement women;

    public void verify_home_page_title() {
        SeleniumHelper.verifyTitle(driver, properties.getProperty("title"));
    }


    public void click_on_homepage_header() {
        SeleniumHelper.click_web_elements(driver, all_header_homepage);
    }


    public void verify_header_homepage() {
        ArrayList<Object> expected_text = HomePageDataProvider.homepage_header; //spreadsheet
        ArrayList<Object> actual_text = SeleniumHelper.getActualText(all_header_homepage);
        SeleniumHelper.compareValue(actual_text, expected_text);

    }

    public WomenPage click_on_women() {
        SeleniumHelper.click_by_text(driver, all_header_homepage, "WOMEN");
        return new WomenPage(driver);
    }

    public LoginPage click_sign_in() {
        SeleniumHelper.click_by_text(driver, all_a_tag, "sign in");
        return new LoginPage(driver);
    }


    public void scrollDownOnHomePage() throws InterruptedException {
        SeleniumHelper.scroll_down_js(driver, 500);
        Thread.sleep(10000);
    }

    public void hover_over_on_header() throws InterruptedException {
        SeleniumHelper.hover_over_and_click(driver, women);
        Thread.sleep(5000);
    }



}
