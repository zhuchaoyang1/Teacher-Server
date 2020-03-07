package cn.usts.controller;

import cn.usts.pojo.College;
import cn.usts.pojo.Marjor;
import cn.usts.pojo.SysUser;
import cn.usts.pojo.vo.BatchImport;
import cn.usts.service.CollegeService;
import cn.usts.service.MarjorService;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import cn.usts.util.enums.SysUserCheck;
import cn.usts.util.excel.CascadeComboBox;
import cn.usts.util.jxls.JXlsExcelHelper;
import cn.usts.util.websocket.SpringWebSocketHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/batch")
public class BatchTeacherFileController {

    @Resource
    private UserService userService;

    @Autowired
    private SpringWebSocketHandler springWebSocketHandler;

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private CascadeComboBox cascadeComboBox;

    @Autowired
    private MarjorService marjorService;

    /**
     * 下载模板
     *
     * @param
     * @return
     */
//    @RequestMapping("/down/teacher/template")
//    public void exportTemplate(HttpServletResponse response) {
//        try {
//            InputStream inputStream = new ClassPathResource("templete/export_teachers_template.xlsx").getInputStream();
//            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
//            response.setContentType("application/form-data;charset=UTF-8");
//            response.setHeader("content-disposition", "attachment;filename="
//                    + URLEncoder.encode(System.currentTimeMillis() + "template.xlsx", "UTF-8"));
//            response.flushBuffer();
//            wb.write(response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @RequestMapping("/down/teacher/template")
//    public void write(HttpServletResponse response) throws IOException, ClassNotFoundException {
//        //初始一个workbook
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        //创建一个sheet
//        HSSFSheet sheet = workbook.createSheet("teachers");
//
//        // 编写眉头
//        HSSFRow row = sheet.createRow(0);
//
//        HSSFCell cell1 = row.createCell(0);
//        cell1.setCellValue("姓名");
//
//        HSSFCell cell2 = row.createCell(1);
//        cell2.setCellValue("账号");
//
//        HSSFCell cell3 = row.createCell(2);
//        cell3.setCellValue("密码");
//
//        HSSFCell cell4 = row.createCell(3);
//        cell4.setCellValue("角色");
//
//        HSSFCell cell5 = row.createCell(4);
//        cell5.setCellValue("手机号");
//
//        HSSFCell cell6 = row.createCell(5);
//        cell6.setCellValue("专业");
//
//        HSSFCell cell7 = row.createCell(6);
//        cell7.setCellValue("学院");
//
//
//
//
//        // 获取所有College 准备下拉列表数据
//        List<String> listCollege = collegeService.queryAllCollegeNames();
//        String[] strs = listCollege.toArray(new String[listCollege.size()]);
//        //设置第一列的1-10行为下拉列表
//        CellRangeAddressList regions = new CellRangeAddressList(1, 10000, 6, 6);
//        //创建下拉列表数据
//        DVConstraint constraint = DVConstraint.createExplicitListConstraint(strs);
//        //绑定
//        HSSFDataValidation dataValidation = new HSSFDataValidation(regions, constraint);
//        sheet.addValidationData(dataValidation);
//
//
//        response.setContentType("application/form-data;charset=UTF-8");
//        response.setHeader("content-disposition", "attachment;filename="
//                + URLEncoder.encode(System.currentTimeMillis() + "template.xls", "UTF-8"));
//        response.flushBuffer();
//        workbook.write(response.getOutputStream());
//    }
    @RequestMapping("/down/teacher/template")
    public void write(HttpServletResponse response) throws IOException, ClassNotFoundException {
        HSSFWorkbook workbook = new HSSFWorkbook();//excel文件对象
        HSSFSheet userinfosheet1 = workbook.createSheet("teachers");//工作表对象


        // 1.创建一个隐藏sheet 用于渲染所有College下的Marjor
        List<String[]> cascadeData = new ArrayList<>();
        // 查询所有College
        List<String> listCollegeNames = collegeService.queryAllCollegeNames();

        if (listCollegeNames.size() > 0) {
            // 添加所有的学院记录
            cascadeData.add(listCollegeNames.toArray(new String[listCollegeNames.size()]));

            listCollegeNames.forEach(item -> {
                // 根据学院名称查询旗下的所有专业
                List<String> currMarjorNames = marjorService.findAllmarjorNamesByCollege(new Marjor(item));
                currMarjorNames.add(0, item);
                cascadeData.add(currMarjorNames.toArray(new String[currMarjorNames.size()]));
            });

            cascadeComboBox.createHideSheet(workbook, cascadeData, "dictionary");
            String[] marjors = {"学院领导", "教务主任", "系（专业）主任", "教职工", "教学督导"};
            List<String[]> marjorsData = new ArrayList<>();
            marjorsData.add(marjors);
            cascadeComboBox.createHideSheet(workbook, marjorsData, "marjor");

            //设置名称数据集
            cascadeComboBox.creatExcelNameList(cascadeData, workbook);
            cascadeComboBox.creatExcelNameListMarjor(workbook);
            //创建一行数据
            cascadeComboBox.creatAppRow(workbook, userinfosheet1, 1);

            //生成输入文件
            response.setContentType("application/form-data;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(System.currentTimeMillis() + "template.xls", "UTF-8"));
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } else {


        }

    }


    /**
     * 导入数据
     */
    @RequestMapping("/import/teachers")
    @ResponseBody
    public JSONBean importTempleteData(@RequestParam("file") MultipartFile file, @RequestParam("socketId") String socketId) {
        Map<String, Object> importData = new HashMap<>();
        importData.put("teachers", new ArrayList<SysUser>());
        log.info("正在导入Teachers记录...");
        try (InputStream importConfigXML = new ClassPathResource("templete/import_teachers_template.xml").getInputStream();
             InputStream importFile = file.getInputStream()) {
            JXlsExcelHelper.getInstance().importExcel(importConfigXML, importFile, importData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("导入记录失败:{}", e.getMessage());
            throw new RuntimeException("导入失败");
        }
        List<SysUser> teachersFromFile = (List<SysUser>) importData.get("teachers");
        log.info("批量导入教师数目是：", teachersFromFile.size());

        List<String> allCollegeNames = collegeService.queryAllCollegeNames();

        // 进行插入数据库
        teachersFromFile.stream().forEach(item -> {
            if (item.getPhone().contains("E") ||
                    item.getName().contains("E") ||
                    item.getPassword().contains("E")) {
                springWebSocketHandler.sendMessageToUser(socketId,
                        new TextMessage("Failed： 教师 " + item.getRealName() + " 所有数据必须输入字符形数据"
                        )
                );
            } else {
                // 验证数据完整性
                String vailadData = validateData(item);
                List<SysUser> list;
                if (StringUtils.isEmpty(vailadData)) {
                    // 校验学院数据是否正确 防止胡乱输入
                    if (allCollegeNames.contains(item.getCollege())) {
                        item.setRole(this.roleStrToInt(item.getRoleStr()));
                        // 学院名称输入正确
                        // 数据完整 可以进行插入数据库
                        list = userService.queryByRealNameAndUsernameAndCollege(
                                new SysUser(
                                        item.getRealName(),
                                        item.getName(),
                                        item.getCollege())
                        );
                        log.info("教师{}记录，账号：{}，密码：{}", item.getRealName(), item.getName(), item.getPassword());
                        if (list.size() == 0) {
                            // 可以插入
                            userService.save(item);
                            springWebSocketHandler.sendMessageToUser(socketId, new TextMessage("Successed： 教师 " + item.getRealName() + " 导入成功"));
                        } else {
                            // 与数据库中重复
                            springWebSocketHandler.sendMessageToUser(socketId, new TextMessage("Failed： 教师 " + item.getRealName() + " 数据已存在，不可重复导入，导入失败"));
                        }
                    } else {
                        // 没有该学院
                        springWebSocketHandler.sendMessageToUser(socketId, new TextMessage("Failed： 教师 " + item.getRealName() + " 学院填写错误，当前学院不存在，可以通过学院管理中先添加学院"));
                    }
                } else {
                    // 该条数据不完整  不可进行插入数据库
                    springWebSocketHandler.sendMessageToUser(socketId, new TextMessage("Failed： 教师数据不完整，不可导入，导入失败"));
                }
            }
        });
        return new JSONBean("200", "success");
    }

    /**
     * 验证数据
     * 0: 管理员  不做清空字段处理
     * 1:学院领导    只绑定院角色   且把专业清空
     * 2:老师     检查学院属性   检查专业属性
     * 3:专业（系）主任   检查学院属性   检查专业属性
     * 4：教务主任   检查学院属性    且把专业清空
     * 5：教学督导   检查学院属性    且把专业清空
     *
     * @param sysUser
     * @return
     */
    private String validateData(SysUser sysUser) {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isEmpty(sysUser.getRoleStr())) {
            // 专业为空
            return SysUserCheck.NO_ROLE_STR.getStr();
        }

        if (sysUser.getRoleStr().equals("学院领导") ||
                sysUser.getRoleStr().equals("教务主任") ||
                sysUser.getRoleStr().equals("教学督导")) {
            sysUser.setMajor("");
        }

        if (StringUtils.isEmpty(sysUser.getRealName())) {
            stringBuilder.append(SysUserCheck.NO_REAL_NAME.getStr() + "，");
        }
        if (StringUtils.isEmpty(sysUser.getName())) {
            stringBuilder.append(SysUserCheck.NO_NAME.getStr() + "，");
        }
        if (StringUtils.isEmpty(sysUser.getPassword())) {
            stringBuilder.append(SysUserCheck.NO_PASS.getStr() + "，");
        }
        // 管理员、院长、教务主任、教务督导  不需要检查
        if (!sysUser.getRoleStr().equals("管理员") &&
                !sysUser.getRoleStr().equals("院长") &&
                !sysUser.getRoleStr().equals("教务主任") &&
                !sysUser.getRoleStr().equals("教务督导") &&
                StringUtils.isEmpty(sysUser.getMajor())) {
            stringBuilder.append(SysUserCheck.NO_MARJOR.getStr() + "，");
        }
        if (StringUtils.isEmpty(sysUser.getCollege())) {
            stringBuilder.append(SysUserCheck.NO_COLLEGE.getStr() + "，");
        }
        return stringBuilder.toString();
    }

    private Integer roleStrToInt(String role) {
        switch (role) {
            case "学院领导": {
                return 1;
            }
            case "教务主任": {
                return 4;
            }
            case "系（专业）主任": {
                return 3;
            }
            case "教职工": {
                return 2;
            }
            case "教学督导": {
                return 5;
            }
        }
        return -1;
    }

}
