package com.pages;

import com.dataProvider.LoginDataProvider;
import com.util.SeleniumHelper;
import org.apache.tools.ant.taskdefs.Sleep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    WebElement email;

    @FindBy(id = "passwd")
    WebElement pass;

    @FindBy(tagName = "button")
    List<WebElement> buttons;

    @FindBy(xpath = "//li[contains(text(),'Invalid email address.')]")
    WebElement errorMsg;

    public void login_to_app() {
        SeleniumHelper.insertData(LoginDataProvider.login_invalid_data(), email, pass);
        SeleniumHelper.click_by_text(driver, buttons, "Sign in");
        String actualMsg = SeleniumHelper.getTextFromElement(errorMsg);
        SeleniumHelper.verifyText(actualMsg, "Invalid email address.");
    }









}
