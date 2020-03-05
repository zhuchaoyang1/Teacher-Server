package cn.usts.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CascadeComboBox {

    /**
     * 名称管理
     */
    public void creatExcelNameList(List<String[]> cascadeData, HSSFWorkbook workbook) {
        for (int i = 0; i < cascadeData.size(); i++) {
            Name name;
            String[] currData = cascadeData.get(i);
            if (i == 0) {
                name = workbook.createName();
                name.setNameName("college");
                name.setRefersToFormula("dictionary!$A$1:$" +
                        (char) ('A' + cascadeData.get(0).length) + "$1");
            } else {
                name = workbook.createName();
                name.setNameName(currData[0]);
                name.setRefersToFormula("dictionary!$B$" + (i + 1) + ":$" +
                        (char) ('B' + cascadeData.get(i).length) + "$" + (i + 1));
            }
        }
    }

    public void creatExcelNameListMarjor(HSSFWorkbook workbook) {
        Name name = workbook.createName();
        name.setNameName("marjor");
        name.setRefersToFormula("marjor!$A$1:$E$1");
    }


    /**
     * 创建隐藏sheet保存用于选择的数据字典信息
     */
    public void createHideSheet(HSSFWorkbook workbook, List<String[]> cascadeData, String hideSheetName) {
        HSSFSheet dictionary = workbook.createSheet(hideSheetName);//隐藏一些信息
        //设置下拉列表的内容
        for (int i = 0; i < cascadeData.size(); i++) {
            HSSFRow row = dictionary.createRow(i);
            this.creatRow(row, cascadeData.get(i));
        }
        //设置隐藏页标志
        workbook.setSheetHidden(workbook.getSheetIndex(hideSheetName), true);
    }

    /**
     * 创建一列应用数据
     *
     * @param naturalRowIndex
     */
    public void creatAppRow(HSSFWorkbook workbook, HSSFSheet sheet, int naturalRowIndex) {
        //构造一个信息输入表单，用户姓名，出生省份，出生城市
        //要求省份是可以下拉选择的，出生城市根据所选择的省份级联下拉选择
        //在第一行第一个单元格，插入下拉框
        HSSFRow row = sheet.createRow(naturalRowIndex - 1);

        HSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("姓名");

        HSSFCell cell4 = row.createCell(1);
        cell4.setCellValue("角色");

        HSSFCell cell5 = row.createCell(2);
        cell5.setCellValue("手机号");
        HSSFCellStyle textStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));
        cell5.setCellStyle(textStyle);//设置单元格格式为"文本"
        cell5.setCellType(HSSFCell.CELL_TYPE_STRING);

        HSSFCell cell2 = row.createCell(3);
        cell2.setCellValue("账号");

        HSSFCell cell3 = row.createCell(4);
        cell3.setCellValue("密码");

        HSSFCell cell6 = row.createCell(5);
        cell6.setCellValue("学院");

        HSSFCell cell7 = row.createCell(6);
        cell7.setCellValue("专业");


        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 14 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);


        //得到验证对象
        DataValidation validation = this.getDataValidationByFormula("college", 2, 6);
        //工作表添加验证数据
        sheet.addValidationData(validation);

        validation = this.getDataValidationByFormula("marjor", 2, 2);
        //工作表添加验证数据
        sheet.addValidationData(validation);

        /*
         * 网上很多资料都是错的 。这里INDIRECT($F1)	F后面必须是1，excel会自动处理
         */
        validation = this.getDataValidationByFormula("INDIRECT($F1)", 2, 7);
        //工作表添加验证数据
        sheet.addValidationData(validation);
    }

    /**
     * 创建一列数据
     *
     * @param currentRow
     * @param textList
     */
    public void creatRow(HSSFRow currentRow, String[] textList) {
        if (textList != null && textList.length > 0) {
            int i = 0;
            for (String cellValue : textList) {
                HSSFCell cell = currentRow.createCell(i++);
                cell.setCellValue(cellValue);
            }
        }
    }

    public DataValidation getDataValidationByFormula(String formulaString, int naturalRowIndex, int naturalColumnIndex) {
        //创建公式约束（数据有效性）
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(formulaString);

        int firstRow = naturalRowIndex - 1;
        int firstCol = naturalColumnIndex - 1;
        int lastCol = naturalColumnIndex - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, 100000, firstCol, lastCol);
        //数据有效性对象
        DataValidation validation = new HSSFDataValidation(regions, constraint);
        return validation;
    }

}
