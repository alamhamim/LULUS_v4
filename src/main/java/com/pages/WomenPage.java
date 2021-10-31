package com.pages;

import com.dataProvider.WomenPageDataProvider;
import com.util.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class WomenPage {
    private WebDriver driver;

    public WomenPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body/div[@id='page']/div[2]/div[1]/div[3]/div[2]/ul[1]/li/div[1]/div[2]/h5[1]/a")
    private List<WebElement> all_women_products;


    public void verify_all_women_product() {
        /*ArrayList<Object> expectedData = new ArrayList<>();
        expectedData.add("Faded Short Sleeve T-shirts");
        expectedData.add("Blouse");
        expectedData.add("Printed Dress");
        expectedData.add("Printed Dress");
        expectedData.add("Printed Summer Dress");
        expectedData.add("Printed Summer Dress");
        expectedData.add("Printed Chiffon Dress");*/

        ArrayList<Object> expectedData = WomenPageDataProvider.women_page_item; //spreadsheet
        ArrayList<Object> actualData = SeleniumHelper.getActualText(all_women_products);
        SeleniumHelper.compareValue(actualData, expectedData);
    }



}
