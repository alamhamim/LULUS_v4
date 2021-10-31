package com.tests;

import com.base.BaseClass;
import com.pages.HomePage;
import com.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    HomePage homePage;
    LoginPage loginPage;

    @Test
    public void verify_login() {
        logger = extent.createTest("Login Verify");
        homePage = new HomePage(driver);
        loginPage = homePage.click_sign_in();
        loginPage.login_to_app();
    }



}
