package com.rough;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class RoughTest {

    @Test
    public void click_on_homepage_header() {
        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("WOMEN");
        expectedData.add("DRESSES");
        expectedData.add("T-SHIRTS");

        System.out.println(expectedData.get(1));

        int count = 0;
        while (count < 100) {
            System.out.println(count);
            count++;
        }

    }




    @Test
    public ArrayList<Object> readData() {
        ArrayList<Object> arrayList = new ArrayList<>();
        try
        {
            File file = new File("/Users/ninja/Downloads/HomePageData.xlsx");   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();    //iterating over excel file
            while (itr.hasNext())
            {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_STRING:    //field that represents string cell type
                            arrayList.add(cell.getStringCellValue());
                            System.out.print(cell.getStringCellValue() + "\t\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:    //field that represents number cell type
                            arrayList.add(cell.getNumericCellValue());
                            System.out.print(cell.getNumericCellValue() + "\t\t\t");
                            break;
                        default:
                    }
                }
                System.out.println("");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return arrayList;
    }
}
