package com.hrms.utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelReading {

   static Workbook book;
   static Sheet sheet;

    /**
     * this nethod opens excel file
     * @param filePath
     */
    public static void openExel(String filePath) {


        try {
            FileInputStream fis = new FileInputStream(filePath);
            book = new XSSFWorkbook(fis);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * this method will allow you to get Sheet Name
     * @param sheetName
     */
    public static void getSheet(String sheetName){
       sheet = book.getSheet(sheetName);

    }

    /**
     * this method will allow you to get row count in a Excel Sheet
     * @return
     */
    public static int getRowCount(){

       return sheet.getPhysicalNumberOfRows();

    }

    /**
     * this method allow us to get the number of colons
     * @param rowIndex
     * @return
     */
    public static int getColsCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();

    }

    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    /**
     * this method turns exel file data into list map
     */
    public static List<Map<String, String>> excelIntoListMap(String filePath, String sheetName ){

        openExel(filePath);
        getSheet(sheetName);
        List<Map<String, String>> listData = new ArrayList();

        for(int row=1; row<getRowCount(); row++){
            //creating a map for every row
            Map<String, String> map = new LinkedHashMap<>();

            /*
            looping through all cell in the row
            outer loop controls rows, starts from second row in the exel
            because we don't need header, its not containing a data to store in the map

            Inner loop is getting the index of column, controlled by our getColsCount method.
            each loop iteration we write the info to our map. our may key is always be at row index 0,
            and depended on column index. and the value to write we are getting by using row index from outer loop,
            and column index from the inner loop.

            this are dynamic loops, there is no hardcoded column counts or row counts.
            so these loops will determine how many iterations we need by calling getRowCount() and getColsCount() methodd
            in our for condition
             */
            for(int col=0; col<getColsCount(row); col++){

                //storing values from ach cell into a map
                map.put(getCellData(0, col), getCellData(row,col) );
            }

            //adding map to the list
            listData.add(map);

        }

        return listData;

    }


}
