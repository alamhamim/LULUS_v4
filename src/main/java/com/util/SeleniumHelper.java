package com.util;

import com.google.common.io.Files;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SeleniumHelper {

    public static String getDateAndTime() {
        SimpleDateFormat date = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        Date currentDate = new Date();
        return date.format(currentDate);
    }

    public static void takeScreenShot(WebDriver driver)  {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(SrcFile, new File("ScreenShots/pic_" + getDateAndTime() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void verifyTitle(WebDriver driver, String title) {
        SoftAssert softAssert = new SoftAssert();
        if (driver.getTitle().equals(title)) {
            System.out.println("Title is verified");
        } else {
            softAssert.fail();
        }
    }


    public static Properties fileReader(String path) {
        FileInputStream file = null;
        Properties properties = null;

        try {
            file = new FileInputStream(new File(path));
            properties = new Properties();
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }


    public static void click_by_text(WebDriver driver, List<WebElement> elements, String expectedText) {
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(expectedText)) {
                element.click();
                break;
            }
        }

    }


    public static void click_web_elements(WebDriver driver, List<WebElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).click();
            driver.navigate().back();
        }
    }


    public static ArrayList<Object> getActualText(List<WebElement> elements) {
        ArrayList<Object> arrayList = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++ ) {
            arrayList.add(elements.get(i).getText().toString());
        }

        return arrayList;
    }


    public static void compareValue(ArrayList<Object> actualValue, ArrayList<Object> expectedValue) {
        Iterator<Object> actual = actualValue.iterator();
        Iterator<Object> expected = expectedValue.iterator();

        while (actual.hasNext() && expected.hasNext()) {

            if (actual.next().toString().equalsIgnoreCase(expected.next().toString())) {
                System.out.println("Verified");

            } else {
                System.out.println("Can not verify");
            }
        }
    }


    public static ArrayList<Object> readDataFromSpreadSheet(String path) {
        ArrayList<Object> arrayList = new ArrayList<>();
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            while (itr.hasNext())
            {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:
                            arrayList.add(cell.getStringCellValue());
                            //System.out.print(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            arrayList.add(cell.getNumericCellValue());
                            //System.out.print(cell.getNumericCellValue());
                            break;
                        default:
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return arrayList;
    }


    public static void typeOnWebElement(WebElement element, String text) {
        element.sendKeys(text);
    }


    public static String getTextFromElement(WebElement element) {
        return element.getText();
    }


    public static void verifyText(String actual, String expected) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual, expected);
    }

    public static void insertData(HashMap<String, String> datas, WebElement user, WebElement pass) {
        Set<Map.Entry<String, String>> data = datas.entrySet();
        Iterator<Map.Entry<String, String>> iterator = data.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> a = iterator.next();
            user.sendKeys(a.getKey());
            pass.sendKeys(a.getValue());
        }

    }

    public static void scroll_down_js(WebDriver driver, int pixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+pixel+")", "");
    }

    public static void hover_over_and_click(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }


}
