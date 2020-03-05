package cn.usts.controller.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.usts.pojo.College;
import cn.usts.pojo.Marjor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class MarjorEasyPOIExport {

    @Autowired
    private EasyPoiUtil easyPoiUtil;

    /**
     * 导出专业多Sheet Excel
     *
     * @param sheetsMarjor
     * @param response
     */
    public void exportEmptyMarjorExcel(List<College> sheetsMarjor, HttpServletResponse response) {
        List<Marjor> userExcels = new ArrayList<>();
        Marjor marjor = new Marjor();
        userExcels.add(marjor);

        List<Map<String, Object>> sheetsList = new ArrayList<>();

        // 创建参数对象
        for (College college : sheetsMarjor) {
            ExportParams exportParams = new ExportParams();
            // 设置sheet得名称
            exportParams.setSheetName(college.getCollegeName());

            Map<String, Object> deptDataMap = new HashMap<>(4);
            // title的参数为ExportParams类型
            deptDataMap.put("title", exportParams);
            // 模版导出对应得实体类型
            deptDataMap.put("entity", Marjor.class);
            // sheet中要填充得数据
            deptDataMap.put("data", userExcels);

            sheetsList.add(deptDataMap);
        }

        // 执行方法
        Workbook workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);

        this.downLoadExcel(System.currentTimeMillis() + "专业模板.xls", response, workbook);
    }

    /**
     * 下载Excel
     *
     * @param fileName
     * @param response
     * @param workbook
     */
    private void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            // 告诉浏览器用什么软件可以打开此文件
            response.setContentType("application/form-data;charset=UTF-8");
            //设置浏览器响应头对应的Content-disposition
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //编码
            response.setCharacterEncoding("UTF-8");
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, List<Marjor>> importMultiSheet(MultipartFile file) {
        Map<String, List<Marjor>> getResultMarjor = new HashMap<>();
        try {
            // 根据file得到Workbook,主要是要根据这个对象获取,传过来的excel有几个sheet页
            Workbook workBook = easyPoiUtil.getWorkBook(file);
            ImportParams params = new ImportParams();
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {

                // 表头在第几行
                params.setTitleRows(0);
                // 距离表头中间有几行不要的数据
                params.setStartRows(0);
                // 第几个sheet页
                params.setStartSheetIndex(numSheet);

                ExcelImportResult<Marjor> result = ExcelImportUtil.importExcelMore(
                        file.getInputStream(),
                        Marjor.class,
                        params);

                getResultMarjor.put(workBook.getSheetName(numSheet), result.getList());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return getResultMarjor;
    }

}
