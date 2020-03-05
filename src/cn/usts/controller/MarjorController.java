package cn.usts.controller;

import cn.usts.controller.util.MarjorEasyPOIExport;
import cn.usts.pojo.Marjor;
import cn.usts.service.CollegeService;
import cn.usts.service.MarjorService;
import cn.usts.util.JSONBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 支持批量导入和单个导入
 */
@Slf4j
@Controller
@RequestMapping("/marjor")
public class MarjorController {

    @Autowired
    private MarjorService marjorService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private MarjorEasyPOIExport marjorEasyPOIExport;


    @RequestMapping("/query")
    @ResponseBody
    public JSONBean queryAll() {
        return new JSONBean("0", marjorService.queryAll());
    }

    @RequestMapping("/delete/id")
    @ResponseBody
    public JSONBean deleteById(@RequestParam Integer id) {
        marjorService.delete(Marjor.builder().id(id).build());
        return new JSONBean("0", "success");
    }

    /**
     * 单个导入专业
     *
     * @param marjor
     * @return
     */
    @RequestMapping("/single/import")
    @ResponseBody
    public JSONBean singleImportMarjor(@RequestBody Marjor marjor) {
        if (StringUtils.isEmpty(marjor.getCollegeId())) {
            // 学院为空
            return new JSONBean("0", "学院为空，无法保存专业");
        }
        marjorService.saveMarjor(marjor);
        return new JSONBean("0", marjor.getId());
    }

    /**
     * 为批量导入专业提供模板
     *
     * @return
     */
    @RequestMapping("/batch/template/export")
    @ResponseBody
    public JSONBean batchImportMarjor(HttpServletResponse response) {
        marjorEasyPOIExport.exportEmptyMarjorExcel(collegeService.queryAll(), response);
        return new JSONBean("0", "ok");
    }

    @RequestMapping("/batch/template/import")
    @ResponseBody
    public JSONBean importMarjor(@RequestParam("file") MultipartFile file, @RequestParam("socketId") String socketId) {
        String result = marjorService.batchImport(marjorEasyPOIExport.importMultiSheet(file), socketId);
        return new JSONBean("0",
                StringUtils.isEmpty(
                        result
                ) ? "ok" : result
        );
    }

    @RequestMapping("/findby/college")
    @ResponseBody
    public JSONBean findAllByCollegeName(@RequestBody Marjor marjor) {
        return new JSONBean("0", marjorService.findAllByCollegeName(marjor));
    }


}
