package cn.usts.controller;

import cn.usts.pojo.FormData;
import cn.usts.pojo.page.PageOrSize;
import cn.usts.pojo.vo.FormToken;
import cn.usts.service.FormService;
import cn.usts.util.ConvertTime;
import cn.usts.util.JSONBean;
import cn.usts.util.excel.CreateExcelUtil;
import cn.usts.util.session.SessionContext;
import cn.usts.util.zip.ZipMultiFile;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.*;

/**
 * 项目由表格形成
 * 该Controller负责所有表格数据保存或读取
 * 根据前端传递来的数据表的名称来选择对哪一张表格进行什么样的操作
 *
 * @Author: ${朱朝阳}
 * @Date: 2019/7/16 23:11
 */

@Controller
@RequestMapping("/form")
public class FormController {

    @Resource
    private FormService formService;

//    @Resource
//    private CreateExcelUtil createExcelUtil;

    /**
     * @InitBinder public void initBinder(WebDataBinder binder) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    format.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(format,false));
    }
     * @param binder
     */


    /**
     * 提交Form
     *
     * @param formToken Bean --> FormData和UserToken
     * @return
     */
    @RequestMapping("/submitForm")
    @ResponseBody
    public JSONBean uploadFormData(@RequestBody FormToken formToken, HttpServletRequest request) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = formToken.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        FormData formData = formToken.getFormData();
        formData.setuID(uId);

        Date date = null;
        try {
            date = ConvertTime.timeStrToDate2(formToken.getStrTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formToken.getFormData().setPersonalTime(date);

        formService.save(formData);
        return new JSONBean("success", formData.getpId());
    }


    @RequestMapping("/queryByPageID")
    @ResponseBody
    public JSONBean queryForm(@RequestBody PageOrSize pageOrSize) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = pageOrSize.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        pageOrSize.setId(uId);

        // 获取数据总长度
        int size = formService.getCountSize(pageOrSize);
        // 获取分页数据
        List<FormData> list = formService.pageFormDataByID(pageOrSize);

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("size", size);
        map.put("list", list);

        return new JSONBean("success", map);
    }

    /**
     * 根据ID单个文件下载
     * 传递userToken、filepath、两个参数
     *
     * @param formToken
     * @return
     */
    @RequestMapping("/singalDownload")
    public Object singalFileDownLoad(FormToken formToken, HttpSession session) {
        // 下载文件没有验证accessToken
        FormData formData = formToken.getFormData();

        String logoRealPath = session.getServletContext().getRealPath(formData.getFilepath());

        File file = new File(logoRealPath);

        HttpHeaders headers = new HttpHeaders();
        try {
            String downloadFielName = new String(formData.getFilename().getBytes("UTF-8"), "iso-8859-1");
            headers.setContentDispositionFormData("attachment", downloadFielName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {

        }

        return new JSONBean("error", null);
    }


    /**
     * 有条件方式查询
     * 用于老师或者院领导的多功能筛选下载
     *
     * @param pageOrSize
     * @return
     */
    @RequestMapping("/queryByCon")
    @ResponseBody
    public JSONBean queryFormByConditions(@RequestBody PageOrSize pageOrSize) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = pageOrSize.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        pageOrSize.setId(uId);

        // 获取数据总长度
        int size = formService.formDataByConCount(pageOrSize);

        if (!StringUtils.isEmpty(pageOrSize.getStrTime())) {
            Date date = null;
            try {
                date = ConvertTime.timeStrToDate2(pageOrSize.getStrTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pageOrSize.getFormData().setPersonalTime(date);
        }

        // 获取分页数据
        List<FormData> list = formService.formDataByConditions(pageOrSize);

        Map<Object, Object> map = new HashMap<Object, Object>(2);
        map.put("size", size);
        map.put("list", list);

        return new JSONBean("success", map);
    }


    /**
     * 多文件打包下载
     * 不需要@RequestBody注解，表单提交是www-xxx方式
     *
     * @param pageOrSize
     * @return
     */
    @RequestMapping("/screen")
    @ResponseBody
    public Object screenByCon(PageOrSize pageOrSize, HttpSession session) throws IOException {
        // 获取对应用户Session中保存的用户ID
        String accessToken = pageOrSize.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        javax.servlet.ServletContext context = session.getServletContext();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        pageOrSize.setId(uId);

        if (!StringUtils.isEmpty(pageOrSize.getStrTime())) {
            Date date = null;
            try {
                date = ConvertTime.timeStrToDate(pageOrSize.getStrTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pageOrSize.getFormData().setPersonalTime(date);
        }

        // 查询所有记录  不分页
        List<FormData> list = formService.formDataByConNoPage(pageOrSize);


        //==============================创建Excel=============================
        CreateExcelUtil createExcelUtil = new CreateExcelUtil();

        String logoRealPath = context.getRealPath("/upload/temp/");
        File excelFile = createExcelUtil.createExcel("筛选综合下载", list, logoRealPath + UUID.randomUUID() + "temp.xls");

        // 获取项目/upload/temp文件夹，用于临时存放excel表格以及压缩文件
        File file = new File(logoRealPath + UUID.randomUUID() + ".zip");
        File[] files = new File[(list.size() + 1)];
        Iterator<FormData> iterator = list.iterator();

        int index = 0;
        while (iterator.hasNext()) {
            FormData formData = iterator.next();
            files[index++] = new File(context.getRealPath(formData.getFilepath()));
        }

        files[index] = excelFile;

        /**
         * 两个参数：
         * 参数一：需要打包的所有文件
         * 参数二：Zip文件
         */
        new ZipMultiFile().zipFiles(files, file);

        HttpHeaders headers = new HttpHeaders();
        try {
            String downloadFielName = new String("筛选下载.zip".getBytes("UTF-8"), "iso-8859-1");
            headers.setContentDispositionFormData("attachment", downloadFielName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {

        }

        return new JSONBean("success", null);
    }


}
