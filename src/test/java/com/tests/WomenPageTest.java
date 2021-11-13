package com.tests;

import com.base.BaseClass;
import com.pages.HomePage;
import com.pages.WomenPage;
import org.testng.annotations.Test;

public class WomenPageTest extends BaseClass {


    HomePage homePage;
    WomenPage womenPage;

    /*@Test(priority = 7)
    public void verify_women_page_title_test() {
        homePage = new HomePage(driver);
        womenPage = homePage.click_on_women();
        womenPage.verify_women_page_title();
    }*/

    @Test(priority = 8)
    public void verify_all_product_women_page() {
        logger = extent.createTest("Verify Womenpage product");
        homePage = new HomePage(driver);
        womenPage = homePage.click_on_women();
        womenPage.verify_all_women_product();
    }

    @Test(priority = 9)
    public void verify_hover_over_test() throws InterruptedException {
        logger = extent.createTest("Hover over test on Women Products");
        homePage = new HomePage(driver);
        womenPage = homePage.click_on_women();
        womenPage.hover_over_all_product();
    }

    @Test(priority = 10)
    public void verify_add_to_cart_test() throws InterruptedException {
        logger = extent.createTest("Add to cart");
        homePage = new HomePage(driver);
        womenPage = homePage.click_on_women();
        womenPage.add_to_cart_hover_over();
        womenPage.verify_add_to_cart();
    }

}
