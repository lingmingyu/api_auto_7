package com.lemon.auto.V4;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.lang.reflect.Method;

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
    /*解析指定excel表单的数据，封装为对象
    *excelPath:excel文件的相对路径
    * sheetName:excel表单名
    * */
    public static<T> void load(String excelPath, String sheetName, Class<T> clazz) {
        //创建workBook对象
        try {
            Workbook workbook=WorkbookFactory.create(new File(excelPath));
            Sheet sheet= workbook.getSheet(sheetName);
            //获取第一行
            Row titleRow = sheet.getRow(0);
            //获取最后一列的列号
            int lastCellNum =titleRow.getLastCellNum();
            String [] fields =new String[lastCellNum] ;
            //循环处理每一列,取出每一列里面的字段名，保存到数组
            //拿到此数据行上面的每一列，将数据封装到cs对象
            for (int i =0;i<lastCellNum;i++){
                //根据列索引获取对应的列
                Cell cell= titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //设置列的类型为字符串
                cell.setCellType(CellType.STRING);
                 //获取列值
                String title = cell.getStringCellValue();
                title = title.substring(0,title.indexOf("("));
                fields [i] =title;
            }
            int lastRowIndex = sheet.getLastRowNum();
            //循环处理每一个数据行
            for (int i =1;i<=lastRowIndex;i++){
                Object obj =clazz.newInstance();
                //拿到一个数据行
                Row dataRow  =sheet.getRow(i);
                if (dataRow==null || isEmptyRow(dataRow)){
                    continue;
                }
                //拿到此数据行上面的每一列
                for (int j = 0; j <lastCellNum ; j++) {
                    Cell cell =dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    //获取要反射的方法名
                    String methodName = "set"+fields[j];
                    //获取要反射的方法
                    Method method = clazz.getMethod(methodName,String.class);
                    //完成反射调用
                    method.invoke(obj,value);

                }
                //
                if (obj instanceof Case){//instanceof 是判断对象类型的语法
                    Case cs = (Case) obj;
                    CaseUtil.cases.add(cs);
                }else if (obj instanceof  Rest){
                    Rest rest =(Rest)  obj;
                    RestUtil.rests.add(rest);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            //创建sheet对象a
            //获取行
            //获取列

        }
    }

    public static boolean isEmptyRow(Row dataRow){
        int lastCellNum = dataRow.getLastCellNum();
        for (int i = 0; i < lastCellNum ; i++) {
            Cell cell=dataRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String value =cell.getStringCellValue();
            if (value!=null && value.trim().length()>0){
                return false;
            }

        }
        return  true;
    }

}
