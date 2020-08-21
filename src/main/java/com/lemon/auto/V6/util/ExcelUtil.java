package com.lemon.auto.V6.util;

import com.lemon.auto.V6.cases.Case;
import com.lemon.auto.V6.pojo.Rest;
import com.lemon.auto.V6.pojo.WriteBackData;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class   ExcelUtil {
    public static Map<String,Integer> caseIdRownumMapping =new HashMap<String, Integer>();
    public static Map<String,Integer> cellNameCellnumMapping =new HashMap<String, Integer>();
    public static List<WriteBackData> writeBackDatas =new ArrayList<WriteBackData>();

    static {
        loadRownumAndCellnumMapping("src/test/resources/cases_v6.xls","用例");
    }

    /*
    * startRow 传行号非索引
    *
    * */
    public static Object[][] datas(String excelPath,int startRow,int endRow,int startCell,int endCell) {
        Object[][] datas = null;
        //获取Workbook对象
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取sheet对象
            Sheet sheet = workbook.getSheet("用例");
            //获取行
            datas = new Object[endRow-startRow+1][endCell-startCell+1 ];
            for (int i = startRow; i <= endRow; i++) {
                Row row = sheet.getRow(i-1);
                for (int j = startCell; j <= endCell; j++) {
                    Cell cell = row.getCell(j-1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //将列设置为字符串类型
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    datas[i-startRow][j-startCell] = value;
                }
            }
            //获取列
        } catch (Exception e) {
            e.printStackTrace();

        }
        return datas;
    }

    /*
    * 获取caseid以及他对应的行索引
    * 获取cellname以及它对应的列索引
    * */
    private static void loadRownumAndCellnumMapping(String excelpath, String sheetName) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelpath));
            Workbook workbook =WorkbookFactory.create(inputStream);
            Sheet sheet =workbook.getSheet(sheetName);
            //拿到标题行
            Row titlerow= sheet.getRow(0);
            //如果不为空且不是一个空行拿到所有的列
            if (titlerow!=null){
                int lastCellnum = titlerow.getLastCellNum();
                //循环处理标题行的每一列
                for (int i = 0; i < lastCellnum; i++) {
                    //传入行索引，拿到指定策略
                    Cell cell=titlerow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    //设置为字符串类型
                    cell.setCellType(CellType.STRING);
                    //取出标题行的值
                    String title =cell.getStringCellValue();
                    //取出标题的每个值截取（
                    title =  title.substring(0,title.indexOf("("));
                    //拿到每一列的索引位置
                    int cellnum = cell.getAddress().getColumn();
                    //标题，位置存入map当中
                    cellNameCellnumMapping.put(title,cellnum);

                }
            }
            //从第二行开始，获取所有的数据行，拿到用例编号，和对应的行索引
            //拿到最后一行的行号
            int lastRownum = sheet.getLastRowNum();
            //循环拿到每一个数据行
            for (int i = 1; i < lastRownum ; i++) {
                //拿到行对象
                Row dataROW =sheet.getRow(i);
                //拿到第一列的数据
                Cell firstCellofRow =dataROW.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //设置为字符串类型的列
                firstCellofRow.setCellType(CellType.STRING);
                String  caseId = firstCellofRow.getStringCellValue();
                //拿到行索引信息
                int rownum = dataROW.getRowNum();
                //用例编号和用例编号所在的行索引放进map
                caseIdRownumMapping.put(caseId,rownum);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭流对象
            try {
                if (inputStream!=null){
                    inputStream.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
    /*excelPath 用例文件路径
    * rows  行号数组
    * cells 列号数组
    * */

    public static Object[][] datas(String excelPath,String sheetName,int [] rows,int [] cells) {
        Object[][] datas = null;
        //获取Workbook对象
        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //获取sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
            //获取行
            datas = new Object[rows.length][cells.length];
            //通过循环获取每一行
            for (int i = 0; i < rows.length; i++) {
                //根据行索引取出一行
                Row row = sheet.getRow(rows[i]-1);
                for (int j = 0; j < cells.length; j++) {
                    Cell cell = row.getCell(cells[j]-1 , Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
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

    /*解析指定excel表单的数据,封装为对象
    * excelPath  文件的相对路径
    * sheetName  文件表单名
    * */
    public static<T> void load(String excelPath, String sheetName, Class<T> clazz) {
         //创建WorkBook对象
        try {
            Workbook workbook =WorkbookFactory.create(new File(excelPath));
            Sheet sheet= workbook.getSheet(sheetName);
            //获取第一行
            Row titleRow = sheet.getRow(0);
            //获取最后一列的列号,取出每一列里面的字段名，保存到数组
            int lastCellNum = titleRow.getLastCellNum();
            String [] filelds =new String[lastCellNum];

            //循环处理每一列
            for (int i = 0; i <lastCellNum ; i++) {
                //根据列索引获取对应的列
               Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
               //设置列的类型为字符串
               cell.setCellType(CellType.STRING);
               String title =cell.getStringCellValue();
               title=title.substring(0,title.indexOf("("));
               filelds[i] =title;
            }
            int lastRowIndoex =sheet.getLastRowNum();
            //循环处理每一个数据行
            for (int i = 1; i <=lastRowIndoex ; i++) {
                Object obj =  clazz.newInstance();
                //拿到一个数据行
                Row dataRow = sheet.getRow(i);
                if(dataRow==null||isEmptyRow(dataRow)){
                    continue;
                }
                //拿到此数据行上面的每一列,将数据封装到对象里面去
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = dataRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    //获取要反射的方法名
                    String methodName ="set"+filelds[j];
                    //获取要反射的的方法对象
                    Method method= clazz.getMethod(methodName,String.class);
                    //完成反射调用
                    method.invoke(obj,value);
                }
                //
                if (obj instanceof Case){//instanceof 是判断对象类型的语法
                    Case cs=(Case) obj;
                    CaseUtil.cases.add(cs);
                }else if (obj instanceof Rest){
                    Rest rest=(Rest) obj;
                    RestUtil.rests.add(rest);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建sheet对象
         //获取行
         //获取列

    }

    private static boolean isEmptyRow(Row dataRow) {
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
    /*回写接口响应报文
    * excelPath   excel文件路径
    * */

    public static void writeBackData(String excelPath, String sheetName, String caseId,String cellName,String result) {
        System.out.println("读写excel");
        //创建流对象
        InputStream inputStream = null;
        //创建输出流
        OutputStream outputStream=null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            //传入流对象获得workbook对象
            Workbook workbook =WorkbookFactory.create(inputStream);
            Sheet sheet =workbook.getSheet(sheetName);
            //获取行索引
            int rownum =caseIdRownumMapping.get(caseId);
            Row row = sheet.getRow(rownum);
            int cellnum =cellNameCellnumMapping.get(cellName);
            //获取列索引
            Cell cell =row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            //设置列的类型
            cell.setCellType(CellType.STRING);
            //值写入
            cell.setCellValue(result);
            outputStream =new FileOutputStream(new File(excelPath));
            //写入输出流
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭输入输出流
            try{
                if(outputStream!=null){
                outputStream.close();
            }
            if (inputStream!=null){
                inputStream.close();
            }}catch (Exception e2){
                e2.printStackTrace();
            }

        }
    }
    /*批量回写数据的方法
    * */
    public static void batchWriteBackDatas(String excelPath){
        InputStream inputStream = null;
        OutputStream outputStream=null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            //遍历数据集合
            for (WriteBackData writeBackData :writeBackDatas){
                //获取sheetname
                String sheetName =writeBackData.getSheetName();
                Sheet sheet =workbook.getSheet(sheetName);
                //拿到caseid
                String caseId =writeBackData.getCaseId();
                //拿到行索引
                int rownum = caseIdRownumMapping.get(caseId);
                //拿到行
                Row row =sheet.getRow(rownum);
                //取出列明
                String cellName =writeBackData.getCellName();
                //拿到列索引
                int cellnum =cellNameCellnumMapping.get(cellName);
                Cell cell=row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String result =writeBackData.getResult();
                cell.setCellValue(result);
            }
            outputStream =new FileOutputStream(new File(excelPath));
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (outputStream!=null){
                    outputStream.close();
                }
                if (inputStream!=null){
                    inputStream.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }

        }


    }
}
