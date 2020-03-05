import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/16 13:34
 */
public class CascadeComboBox {

    public static void main(String[] args) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook();//excel文件对象
//        HSSFSheet userinfosheet1 = workbook.createSheet();//工作表对象
//        //创建一个隐藏sheet
//        CascadeComboBox.createHideSheet(workbook, "dictionary");
//        //设置名称数据集
//        CascadeComboBox.creatExcelNameList(workbook);
//        //创建一行数据
//        CascadeComboBox.creatAppRow(userinfosheet1, 1);
//
//        //生成输入文件
//        FileOutputStream out = new FileOutputStream("d:/success12w.xls");
//        workbook.write(out);
//        out.close();
        String phone = "1.8114648363E10";
        System.out.println(phone.contains("E"));
    }

    /**
     * 名称管理
     *
     * @param workbook
     */
    public static void creatExcelNameList(HSSFWorkbook workbook) {
        Name name = workbook.createName();
        name.setNameName("province");
        name.setRefersToFormula("dictionary!$A$1:$E$1");

        name = workbook.createName();
        name.setNameName("浙江");
        name.setRefersToFormula("dictionary!$B$2:$K$2");

        name = workbook.createName();
        name.setNameName("山东");
        name.setRefersToFormula("dictionary!$B$3:$I$3");

        name = workbook.createName();
        name.setNameName("江西");
        name.setRefersToFormula("dictionary!$B$4:$E$4");

        name = workbook.createName();
        name.setNameName("江苏");
        name.setRefersToFormula("dictionary!$B$5:$I$5");

        name = workbook.createName();
        name.setNameName("四川");
        name.setRefersToFormula("dictionary!$B$6:$K$6");
    }


    /**
     * 创建隐藏sheet保存用于选择的数据字典信息
     *
     * @param workbook
     * @param hideSheetName
     */
    public static void createHideSheet(HSSFWorkbook workbook, String hideSheetName) {
        HSSFSheet dictionary = workbook.createSheet(hideSheetName);//隐藏一些信息
        //设置下拉列表的内容
        String[] provinceList = {"浙江", "山东", "江西", "江苏", "四川"};
        String[] zjProvinceList = {"浙江", "杭州", "宁波", "温州", "台州", "绍兴", "金华", "湖州", "丽水", "衢州", "舟山"};
        String[] sdProvinceList = {"山东", "济南", "青岛", "烟台", "东营", "菏泽", "淄博", "济宁", "威海"};
        String[] jxProvinceList = {"江西", "南昌", "新余", "鹰潭", "抚州"};
        String[] jsProvinceList = {"江苏", "南京", "苏州", "无锡", "常州", "南通", "泰州", "连云港", "徐州"};
        String[] scProvinceList = {"四川", "成都", "绵阳", "自贡", "泸州", "宜宾", "攀枝花", "广安", "达州", "广元", "遂宁"};
        //在隐藏页设置选择信息
        HSSFRow provinceRow = dictionary.createRow(0);
        CascadeComboBox.creatRow(provinceRow, provinceList);
        HSSFRow zjProvinceRow = dictionary.createRow(1);
        CascadeComboBox.creatRow(zjProvinceRow, zjProvinceList);
        HSSFRow sdProvinceRow = dictionary.createRow(2);
        CascadeComboBox.creatRow(sdProvinceRow, sdProvinceList);
        HSSFRow jxProvinceRow = dictionary.createRow(3);
        CascadeComboBox.creatRow(jxProvinceRow, jxProvinceList);
        HSSFRow jsProvinceRow = dictionary.createRow(4);
        CascadeComboBox.creatRow(jsProvinceRow, jsProvinceList);
        HSSFRow scProvinceRow = dictionary.createRow(5);
        CascadeComboBox.creatRow(scProvinceRow, scProvinceList);
        //设置隐藏页标志
        workbook.setSheetHidden(workbook.getSheetIndex(hideSheetName), true);
    }

    /**
     * 创建一列应用数据
     *
     * @param naturalRowIndex
     */
    public static void creatAppRow(HSSFSheet sheet, int naturalRowIndex) {
        //构造一个信息输入表单，用户姓名，出生省份，出生城市
        //要求省份是可以下拉选择的，出生城市根据所选择的省份级联下拉选择
        //在第一行第一个单元格，插入下拉框
        HSSFRow row = sheet.createRow(naturalRowIndex - 1);
        HSSFCell provinceLableCell = row.createCell(0);
        provinceLableCell.setCellValue("省份:");
        HSSFCell provinceCell = row.createCell(1);
        provinceCell.setCellValue("请选择");
        HSSFCell cityLableCell = row.createCell(2);
        cityLableCell.setCellValue("城市:");
        HSSFCell cityCell = row.createCell(3);
        cityCell.setCellValue("请选择");

        //得到验证对象
        DataValidation validation = CascadeComboBox.getDataValidationByFormula("province", 2, 2);
        //工作表添加验证数据
        sheet.addValidationData(validation);


        /*
         * 网上很多资料都是错的 。这里INDIRECT($B1)	D后面必须是1，excel会自动处理
         */
        validation = CascadeComboBox.getDataValidationByFormula("INDIRECT($B1)", 2, 4);
        //工作表添加验证数据
        sheet.addValidationData(validation);
    }

    /**
     * 创建一列数据
     *
     * @param currentRow
     * @param textList
     */
    public static void creatRow(HSSFRow currentRow, String[] textList) {
        if (textList != null && textList.length > 0) {
            int i = 0;
            for (String cellValue : textList) {
                HSSFCell cell = currentRow.createCell(i++);
                cell.setCellValue(cellValue);
            }
        }
    }

    /**
     * 使用已定义的数据源方式设置一个数据验证
     *
     * @param formulaString
     * @param naturalRowIndex
     * @param naturalColumnIndex
     * @return
     */
    public static DataValidation getDataValidationByFormula(String formulaString, int naturalRowIndex, int naturalColumnIndex) {
        //创建公式约束（数据有效性）
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(formulaString);

        int firstRow = naturalRowIndex - 1;
        int lastRow = naturalRowIndex - 1;
        int firstCol = naturalColumnIndex - 1;
        int lastCol = naturalColumnIndex - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        //数据有效性对象
        DataValidation validation = new HSSFDataValidation(regions, constraint);
        return validation;
    }

}
