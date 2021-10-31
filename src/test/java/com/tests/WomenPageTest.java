package com.tests;

import com.base.BaseClass;
import com.pages.HomePage;
import com.pages.WomenPage;
import org.testng.annotations.Test;

public class WomenPageTest extends BaseClass {


    HomePage homePage;
    WomenPage womenPage;

    @Test
    public void verify_all_product_womenpage() {
        logger = extent.createTest("Verify Womenpage product");
        homePage = new HomePage(driver);
        womenPage = homePage.click_on_women();
        womenPage.verify_all_women_product();
    }




}
