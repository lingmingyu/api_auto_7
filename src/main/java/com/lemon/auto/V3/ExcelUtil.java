package com.lemon.auto.V3;

import org.apache.poi.ss.usermodel.*;

import java.io.File;

public class ExcelUtil {
    /*
    *startRow 传开始行号
    *
    * */
    public static Object [][] datas(String excelPath,String sheetName,int startRow,int endRow,int startCell,int endCell ) {
        Object[][] datas = null;
        //获取Workbook对象
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
            //获取行
             datas = new Object[endRow-startRow+1][endCell-startCell+1];
            for (int i = startRow; i <= endRow; i++) {
                Row row = sheet.getRow(i-1);
                for (int j = startCell; j <= endCell; j++) {
                    Cell cell = row.getCell(j-1);
                    //将列设置为字符串类型
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    datas[i - startRow][j - startCell] = value;
                }
            }
            //获取列

        } catch (Exception e) {
            e.printStackTrace();


        }
        return datas;
    }

    /*excelPath 文件的路径
     * rows 行号
     *
     * */
    public static Object[][] datas(String excelPath,String sheetName,int [] rows,int [] cells) {
        Object[][] datas = null;
        //获取Workbook对象
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
            //定义保存数据的数组
            datas = new Object[rows.length] [cells.length];
            //通过循环获取每一行
            for (int i =0 ; i < rows.length; i++) {
                //根据行索引取出一行数据
                Row row = sheet.getRow(rows[i]-1);
                for (int j = 0; j <cells.length ; j++) {
                    Cell cell = row.getCell(cells[j]-1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //将列设置为字符串类型
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    datas[i][j] = value;
                }
            }
            //获取列
        } catch (Exception e) {
            e.printStackTrace();

        }
        return datas;
    }
}
