package com.pages;

import com.dataProvider.LoginDataProvider;
import com.util.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Properties;

public class LoginPage {

    private WebDriver driver;
    private Properties properties;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        properties = SeleniumHelper.fileReader("src/main/resources/PageData/login_page.properties");
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "passwd")
    private WebElement pass;

    @FindBy(tagName = "button")
    private List<WebElement> buttons;

    @FindBy(xpath = "//li[contains(text(),'Invalid email address.')]")
    private WebElement errorMsg;

    public void login_to_app() {
        SeleniumHelper.insertData(LoginDataProvider.login_invalid_data(), email, pass);
        SeleniumHelper.click_by_text(driver, buttons, "Sign in");
        String actualMsg = SeleniumHelper.getTextFromElement(errorMsg);
        SeleniumHelper.verifyText(actualMsg, "Invalid email address.");
    }


}
