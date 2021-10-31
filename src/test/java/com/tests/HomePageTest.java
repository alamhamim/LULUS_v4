package com.tests;

import com.base.BaseClass;
import com.pages.HomePage;
import org.testng.annotations.Test;

public class HomePageTest extends BaseClass {

    HomePage homePage;

    @Test
    public void title_test_homepage() {
        logger = extent.createTest("Verify Homepage Title ");
        homePage = new HomePage(driver);
        homePage.verify_home_page_title();

    }

    @Test
    public void verify_header_text_test_homepage() {
        logger = extent.createTest("verify Homepage header text");
        homePage = new HomePage(driver);
        homePage.verify_header_homepage();

    }

    @Test
    public void verify_header_click_test_homepage() {
        logger = extent.createTest("Click on header on Homepage");
        homePage = new HomePage(driver);
        homePage.click_on_homepage_header();

    }


    @Test
    public void scrollTest() throws InterruptedException {
        logger = extent.createTest("Srcoll down test");
        homePage = new HomePage(driver);
        homePage.scrollDownOnHomePage();

    }


    @Test
    public void hover_over_header_test() throws InterruptedException {
        logger = extent.createTest("Hover over on Header Women");
        homePage = new HomePage(driver);
        homePage.hover_over_on_header();

    }

}
