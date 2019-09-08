package cn.usts.util.excel;

import cn.usts.pojo.FormData;
import cn.usts.util.ConvertTime;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/21 22:06
 */
public class CreateExcelUtil {

    /**
     * 表头 SheetHeaders
     */
    private static final String[] SHEETHEADERS = new String[]{
            "序号", "教师姓名", "所属专业", "类别", "项目名称", "所获奖励或支持名称", "时间",
            "等级/级别", "授予部门", "附件名称", "附件在压缩包中名称"
    };


    /**
     * 创建Excel listData是表格中数据
     * 由Controller传递来
     *
     * @param sheetName -->  工作区名字
     * @param listData  -->  data
     * @return
     */
    public File createExcel(String sheetName, List<FormData> listData, String excelPath) throws IOException {
        // 创建单元格
        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建工作表
        HSSFSheet sheet = wb.createSheet(sheetName);
        // 工作区列宽十个字符
//        sheet.setColumnWidth(0,100*256);
        // 设置工作表表头
        HSSFRow row = sheet.createRow(0);

        // 创建表头的字体样式
        HSSFCellStyle headerStyle = wb.createCellStyle();
        // 列宽自动换行
        headerStyle.setWrapText(true);
        // 创建字体样式
        HSSFFont headerFont = wb.createFont();
        // 设置字体类型
        headerFont.setFontName("黑体");
        // 设置字体大小
        headerFont.setFontHeightInPoints((short) 10);
        // 为标题样式设置字体样式
        headerStyle.setFont(headerFont);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        // 设置数据列字体样式
        HSSFCellStyle columnStyle = wb.createCellStyle();
        columnStyle.setWrapText(true);
        columnStyle.setAlignment(HorizontalAlignment.CENTER);

        for (int i = 0; i < SHEETHEADERS.length; i++) {
            sheet.autoSizeColumn((short) i);
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(SHEETHEADERS[i]);
            cell.setCellStyle(headerStyle);
        }

        // 设置数据内容
        FormData formData = null;
        for (int i = 0; i < listData.size(); i++) {
            formData = listData.get(i);
            // 创建一行
            HSSFRow dataRow = sheet.createRow(i + 1);
            // 创建SHEETHEADERS.Length个单元格
            HSSFCell cell0 = dataRow.createCell(0);
            HSSFCell cell1 = dataRow.createCell(1);
            HSSFCell cell2 = dataRow.createCell(2);
            HSSFCell cell3 = dataRow.createCell(3);
            HSSFCell cell4 = dataRow.createCell(4);
            HSSFCell cell5 = dataRow.createCell(5);
            HSSFCell cell6 = dataRow.createCell(6);
            HSSFCell cell7 = dataRow.createCell(7);
            HSSFCell cell8 = dataRow.createCell(8);
            HSSFCell cell9 = dataRow.createCell(9);
            HSSFCell cell10 = dataRow.createCell(10);
            cell0.setCellValue(i + 1 + "");
            cell1.setCellValue(formData.getSysUser().getRealName());
            cell2.setCellValue(formData.getSysUser().getMajor());
            cell3.setCellValue(formData.getCategory());
            cell4.setCellValue(formData.getTitle());
            cell5.setCellValue(formData.getSupport());
            cell6.setCellValue(ConvertTime.dateToString(formData.getPersonalTime()));
            cell7.setCellValue(formData.getPersonalLevel());
            cell8.setCellValue(formData.getDepartment());
//            cell9.setCellValue(formData.getFilename() + i);
            cell9.setCellValue(formData.getFilename());
            String[] splitPath = formData.getFilepath().split("/");
            cell10.setCellValue(splitPath[splitPath.length - 1]);
            cell0.setCellStyle(columnStyle);
            cell1.setCellStyle(columnStyle);
            cell2.setCellStyle(columnStyle);
            cell3.setCellStyle(columnStyle);
            cell4.setCellStyle(columnStyle);
            cell5.setCellStyle(columnStyle);
            cell6.setCellStyle(columnStyle);
            cell7.setCellStyle(columnStyle);
            cell8.setCellStyle(columnStyle);
            cell9.setCellStyle(columnStyle);
        }

        File file = new File(excelPath);

        // 若父路径不存在则先创建父路径
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }

        if (file.exists()) {
            file.delete();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    public static void main(String[] args) throws IOException {
//        List<FormData> list = new ArrayList<FormData>();
//        Date date = new Date(new java.util.Date().getTime());
//        FormData data1 = new FormData(1,"江苏省类别","小程序大赛","腾讯连手清华大学",date,"江苏省","江苏省",null,null,0,"比赛证书");
//        list.add(data1);
//        new CreateExcelUtil().createExcel("测试",list,"");
    }


}
