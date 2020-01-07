package com.django.springboot2.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @ClassName ExcelUtils
 * @Description
 * @Author liulongyun
 * @Date 2019/12/27 14:53
 * @Version 1.0.0
 **/
public class ExcelUtils {


    public static List<JSONObject> importExcel(String xlsPath, List<String> fieldNames) throws Exception {


        //根据指定的文件输入流导入Excel从而产生Workbook对象
        try(FileInputStream fileIn = new FileInputStream(xlsPath);
            XSSFWorkbook wb0 = new XSSFWorkbook(fileIn)) {

            //获取Excel文档中的第一个表单
            XSSFSheet sheet = wb0.getSheetAt(0);
            //对Sheet中的每一行进行迭代
            List<JSONObject> ret = new ArrayList<>();

            //遍历行 从下标第一行开始（去除标题）
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null) {
                    //装载obj
                    ret.add(dataObj(fieldNames, row));
                }
            }
            return ret;
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<JSONObject> importExcel(InputStream inputStream, List<String> fieldNames) throws Exception {
        List temp = new ArrayList();
        //根据指定的文件输入流导入Excel从而产生Workbook对象
        XSSFWorkbook wb0 = new XSSFWorkbook(inputStream);
        //获取Excel文档中的第一个表单
        XSSFSheet sheet = wb0.getSheetAt(0);
        //对Sheet中的每一行进行迭代
        List<JSONObject> ret = new ArrayList<>();
        //遍历行 从下标第一行开始（去除标题）
        int lastRowNum = sheet.getLastRowNum();
        JSONObject data;
        XSSFRow firstRow = sheet.getRow(0);
        List<String> title = dataToList(firstRow);
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                //装载obj
                data = dataObj(fieldNames, row, title);
                if (null != data) {
                    ret.add(data);
                }
            }
        }
        return ret;
    }

    private static JSONObject dataObj(List<String> fieldNames, XSSFRow row) throws Exception {
        //容器
        JSONObject p = new JSONObject();
        Object value;
        if (null != fieldNames && fieldNames.size() > 0) {
            //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）
            for (int j = 0; j < fieldNames.size(); j++) {
                value = getRightTypeCell(row.getCell(j));
                if (null != value) {
                    p.put(fieldNames.get(j), String.valueOf(value));
                }
            }
        } else {
            Iterator<Cell> iterator = row.cellIterator();
            Integer i = 0;
            while (iterator.hasNext()) {
                value = getRightTypeCell(iterator.next());
                if (null != value) {
                    p.put(i.toString(), String.valueOf(value));
                } else {
                    p.put(i.toString(), "");
                }
                i += 1;
            }
        }
        return p;
    }

    private static JSONObject dataObj(List<String> fieldNames, XSSFRow row, List<String> titles) throws Exception {
        //容器
        JSONObject p = new JSONObject();
        Object value;
        if (null != fieldNames && fieldNames.size() > 0) {
            //注意excel表格字段顺序要和obj字段顺序对齐 （如果有多余字段请另作特殊下标对应处理）
            String fieldName;
            int index;
            for (int j = 0; j < fieldNames.size(); j++) {
                fieldName = fieldNames.get(j);
                index = getCellIndexByName(fieldName, titles);
                value = getRightTypeCell(row.getCell(index));
                if (null != value) {
                    p.put(fieldNames.get(j), String.valueOf(value));
                }
            }
        } else {
            Iterator<Cell> iterator = row.cellIterator();
            Integer i = 0;
            while (iterator.hasNext()) {
                value = getRightTypeCell(iterator.next());
                if (null != value) {
                    p.put(i.toString(), String.valueOf(value));
                } else {
                    p.put(i.toString(), "");
                }
                i += 1;
            }
        }
        return p;
    }

    public static int getCellIndexByName(String name, List<String> titles) {
        int i = 0;
        for (String title : titles) {
            if (name.equals(title)) {
                break;
            }
            i += 1;
        }
        return i;
    }

    private static List dataToList(XSSFRow row) throws Exception {
        //容器
        List<String> ret = new ArrayList<>();
        Object value;
        Iterator<Cell> iterator = row.cellIterator();
        while (iterator.hasNext()) {
            value = getRightTypeCell(iterator.next());
            if (null != value) {
                ret.add(String.valueOf(value));
            } else {
                ret.add("");
            }
        }
        return ret;
    }

    public static Object getRightTypeCell(Cell cell) {
        Object object = null;
        if (null != cell && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING: {
                    object = cell.getStringCellValue();
                    break;
                }
                case XSSFCell.CELL_TYPE_NUMERIC: {
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    object = cell.getNumericCellValue();
                    break;
                }
                case XSSFCell.CELL_TYPE_FORMULA: {
                    cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                    object = cell.getNumericCellValue();
                    break;
                }
                case XSSFCell.CELL_TYPE_BLANK: {
                    cell.setCellType(XSSFCell.CELL_TYPE_BLANK);
                    object = cell.getStringCellValue();
                    break;
                }
            }
        }
        return object;
    }

    public static List<JSONObject> readXls(String fileName, Integer sheetNum, List<String> fieldNameList) throws IOException,
            InvalidFormatException {
        FileInputStream is = new FileInputStream(fileName);
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        Workbook book = null;
        List<JSONObject> shipments = new ArrayList<>();
        if (extensionName.toLowerCase().equals("xls")) {
            book = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals("xlsx")) {
            book = new XSSFWorkbook(is);
        }
        Sheet sheet = book.getSheetAt(sheetNum);
        // 解析公式结果
        FormulaEvaluator evaluator = book.getCreationHelper()
                .createFormulaEvaluator();
        /**
         * 通常第一行都是标题，所以从第二行开始读取数据
         */
        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
            Row row = sheet.getRow(i);
            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            JSONObject obj = new JSONObject();
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                if (colIx >= fieldNameList.size()) {
                    break;
                }
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null) {
                    continue;
                }
//                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
//                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
                switch (cellValue.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        obj.put(fieldNameList.get(colIx), cellValue.getBooleanValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                        if (DateUtil.isCellDateFormatted(cell)) {
                            obj.put(fieldNameList.get(colIx), cell.getDateCellValue());
                        } else {
                            obj.put(fieldNameList.get(colIx), (long)cellValue.getNumberValue());
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        obj.put(fieldNameList.get(colIx), cellValue.getStringValue().trim());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        break;
                    default:
                        break;
                }
                if (StringUtil.isNullOrEmpty(obj.getString(fieldNameList.get(colIx)))) {
                    break;
                }
            }
            shipments.add(obj);
        }
        return shipments;
    }




    public static void exportExcel(ExcelData data, OutputStream out) throws Exception {


        try(XSSFWorkbook wb = new XSSFWorkbook();) {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }

            XSSFSheet sheet = wb.createSheet(sheetName);

            writeExcel(wb, sheet, data);

            wb.write(out);

        }catch(Exception e){
          throw e;
        }
    }






    private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {

        int rowIndex = 0;

        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());

        writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);

        autoSizeColumns(sheet, data.getTitles().size() + 1);

    }


    private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        // titleFont.setFontHeightInPoints((short) 14);
        titleFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);

        Row titleRow = sheet.createRow(rowIndex);
        // titleRow.setHeightInPoints(25);
        colIndex = 0;

        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }

        rowIndex++;
        return rowIndex;
    }

    private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex = 0;

        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        // dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);

        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            // dataRow.setHeightInPoints(25);
            colIndex = 0;

            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }

                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }

    private static void autoSizeColumns(Sheet sheet, int columnNumber) {

        for (int i = 0; i < columnNumber; i++) {

            int orgWidth = sheet.getColumnWidth(i);

            int newWidth = 256 * 20 + 184;

            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }

        }
    }


    /**
     * @Author liulongyun
     * @Description  合并excel表格中每一列中相同值的单元格
     * @Date 2019/12/27 15:12
     * @Param
     * @return
     **/
    public static void mergeColumns(XSSFWorkbook wb,OutputStream os) throws Exception{


        try {
            XSSFSheet sheet1 = wb.getSheet("Sheet1");

            int firstRowNum = sheet1.getFirstRowNum();
            int lastRowNum = sheet1.getLastRowNum();


            short firstCellNum = sheet1.getRow(firstRowNum).getFirstCellNum();
            short lastCellNum = sheet1.getRow(firstRowNum).getLastCellNum();

            for (int cleeNum = firstCellNum;cleeNum<=lastCellNum;cleeNum++){

                // 处理每个列合并

                int mergeStartRowNum = firstRowNum;

                while (mergeStartRowNum<lastRowNum){

                    int mergeEndRowNum = mergeStartRowNum;
                    int equalValRowNum = mergeStartRowNum+1;


                    XSSFRow mergeStartRow = sheet1.getRow(mergeStartRowNum);

                    XSSFRow equalValRow = sheet1.getRow(equalValRowNum);

                    boolean doCheckMerge = (null!=mergeStartRow &&
                            equalValRow != null &&
                            mergeStartRow.getCell(cleeNum)!=null&&
                            equalValRow.getCell(cleeNum) != null&&
                            mergeStartRow.getCell(cleeNum).getCellType() != Cell.CELL_TYPE_BLANK &&
                            equalValRow.getCell(cleeNum).getCellType() != Cell.CELL_TYPE_BLANK );


                    if(doCheckMerge){

                        while (mergeStartRow.getCell(cleeNum).getStringCellValue().
                                equals(equalValRow.getCell(cleeNum).getStringCellValue())){

                            mergeEndRowNum = equalValRowNum;
                            equalValRowNum++;
                            equalValRow = sheet1.getRow(equalValRowNum);

                            if(null == equalValRow || null == equalValRow.getCell(cleeNum) ||
                                equalValRow.getCell(cleeNum).getCellType() == Cell.CELL_TYPE_BLANK){

                                break;

                            }

                        }

                        if(mergeEndRowNum > mergeStartRowNum){

                            sheet1.addMergedRegion(new CellRangeAddress(mergeStartRowNum,mergeEndRowNum,cleeNum,cleeNum));

                        }

                    }


                }

            }

            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }

    }







}
