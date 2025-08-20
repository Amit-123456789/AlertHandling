package com.alerthandling.utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataUtils {
    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static Properties pObj;
    public static CellStyle style;

    static String xfile = System.getProperty("user.dir") + "/testdata/AlertHandlingExecutionOutput.xlsx";
    static String sheetName = "Sheet1";

    public static String getURL(String path) {
        try {
            fi = new FileInputStream(path);
            pObj = new Properties();
            pObj.load(fi);
            return pObj.getProperty("URL");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static void writeDataIntoExcel(int r, int c, String message) {
        try {
            fi = new FileInputStream(xfile);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(r);
            cell = row.createCell(c);
            cell.setCellValue(message);
            fo = new FileOutputStream(xfile);
            workbook.write(fo);
            workbook.close();
            fo.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void fillGreenColor(int r, int c) {
        try {
            fi = new FileInputStream(xfile);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(r);
            cell = row.getCell(c);
            style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            fo = new FileOutputStream(xfile);
            workbook.write(fo);
            workbook.close();
            fo.close();
            fi.close();
        } catch (IOException e) {
            System.out.println("Unable to Color");
        }
    }

    public static void fillRedColor(int r, int c) {
        try {
            fi = new FileInputStream(xfile);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(r);
            cell = row.getCell(c);
            style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            fo = new FileOutputStream(xfile);
            workbook.write(fo);
            workbook.close();
            fo.close();
            fi.close();
        } catch (IOException e) {
            System.out.println("Unable to Color");
        }
    }
}

