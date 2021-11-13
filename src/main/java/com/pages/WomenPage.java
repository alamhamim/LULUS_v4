package com.pages;

import com.dataProvider.WomenPageDataProvider;
import com.util.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WomenPage {
    private WebDriver driver;
    private Properties properties;

    public WomenPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        properties = SeleniumHelper.fileReader("src/main/resources/PageData/women_page.properties");
    }

    @FindBy(tagName = "span")
    private List<WebElement> all_add_to_cart;

    @FindBy(tagName = "a")
    private List<WebElement> all_a_tag;

    @FindBy(xpath = "//b[contains(text(),'Cart')]")
    private WebElement cart;

    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[2]/ul[1]/li[2]/div[1]/div[1]/div[1]/a[1]/img[1]")
    private WebElement blouse;

    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[2]/ul[1]/li/div[1]/div[2]/h5[1]/a")
    private List<WebElement> all_women_products;

    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[2]/ul[1]/li//img")
    private List<WebElement> all_women_products_hover_over;

    @FindBy(xpath = "//body[1]/div[1]/div[1]/header[1]/div[3]/div[1]/div[1]/div[4]/div[1]/div[1]/h2[1]")
    private WebElement add_to_cart_msg;

    public void verify_women_page_title() {
        SeleniumHelper.verifyTitle(driver, System.getProperty("title"));
    }

    public void verify_all_women_product() {
        ArrayList<Object> expectedData = WomenPageDataProvider.women_page_item; //spreadsheet
        ArrayList<Object> actualData = SeleniumHelper.getActualText(all_women_products);
        SeleniumHelper.compareValue(actualData, expectedData);
    }

    public void hover_over_all_product() throws InterruptedException {
        SeleniumHelper.scroll_down_js(driver, 700);
        SeleniumHelper.hover_over_on_elements(driver, all_women_products_hover_over);
    }


    public void add_to_cart_hover_over() throws InterruptedException {
        SeleniumHelper.scroll_down_js(driver, 700);
        SeleniumHelper.hover_over_on_element(driver, blouse);
        Thread.sleep(2000);
        SeleniumHelper.click_by_text(driver, all_add_to_cart, "add to cart");
        Thread.sleep(2000);
        String actualMsg = SeleniumHelper.getTextFromElement(add_to_cart_msg);
        Thread.sleep(2000);
        SeleniumHelper.verifyText(actualMsg, System.getProperty("addToCartMsg"));
        Thread.sleep(2000);
        SeleniumHelper.refresh(driver);
    }

    public void verify_add_to_cart() {
        SeleniumHelper.scroll_down_js(driver, -700);
        SeleniumHelper.hover_over_on_element(driver, cart);
        WebElement actual = SeleniumHelper.get_text_from_elements(all_a_tag, "blouse");
        SeleniumHelper.verify_element_present(actual);
    }



}
