package cn.usts.controller;

import cn.usts.pojo.FormData;
import cn.usts.pojo.SysUser;
import cn.usts.pojo.page.PageOrSize;
import cn.usts.pojo.vo.FormToken;
import cn.usts.service.FormService;
import cn.usts.service.UserService;
import cn.usts.util.ConvertTime;
import cn.usts.util.JSONBean;
import cn.usts.util.excel.CreateExcelUtil;
import cn.usts.util.session.SessionContext;
import cn.usts.util.zip.ZipMultiFile;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;


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

        List<SysUser> listUser = userService.queryById(uId);
        String college = null, marjor = null;
        if (listUser.size() > 0) {
            college = listUser.get(0).getCollege();
            marjor = listUser.get(0).getMajor();
        }

        FormData formData = formToken.getFormData();
        formData.setCollege(college);
        formData.setMarjor(marjor);
        formData.setuID(uId);

        DateTime dateTime = new DateTime(formToken.getStrTime());
        formData.setPersonalTime(dateTime.toDate());

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

        // 对PageOrSize构造学院信息和专业信息
        SysUser user = userService.queryById(uId).get(0);
        // 为不同身份提供不同权限
        pageOrSize.setCollege(user.getCollege());
        pageOrSize.setMarjor(user.getMajor());

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
        // 对PageOrSize构造学院信息和专业信息
        SysUser user = userService.queryById(uId).get(0);
        // 为不同身份提供不同权限
        pageOrSize.setCollege(user.getCollege());
        pageOrSize.setMarjor(user.getMajor());


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
        // 对PageOrSize构造学院信息和专业信息
        SysUser user = userService.queryById(uId).get(0);
        // 为不同身份提供不同权限
        pageOrSize.setCollege(user.getCollege());
        pageOrSize.setMarjor(user.getMajor());

        if (!StringUtils.isEmpty(pageOrSize.getStrTime())) {
            Date date = null;
            try {
                date = ConvertTime.timeStrToDate(pageOrSize.getStrTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pageOrSize.getFormData().setPersonalTime(date);
        }

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

    @RequestMapping("/find/by")
    @ResponseBody
    public JSONBean queryByBeans(@RequestBody FormData formData) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = formData.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        formData.setuID(uId);

        List<FormData> query = formService.queryByBean(formData);

        String str = "0";
        if (query.size() > 0) {
            if (query.get(0).getSysUser() != null) {
                if (query.get(0).getSysUser().getId() != uId) {
                    // 说明暂无权限
                    str = "No Auth";
                }
                // 保密ID
                query.get(0).getSysUser().setId(0);
            }
        } else {
            str = "Empty";
        }

        return new JSONBean(str, query);
    }

    @RequestMapping("/has/file")
    @ResponseBody
    public JSONBean pdHasFile(@RequestBody FormData formData) {
        List<FormData> query = formService.queryByBean(formData);
        return new JSONBean("0", query.get(0));
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONBean updateFormData(@RequestBody FormData formData) {
        DateTime dateTime = new DateTime(formData.getPersonalTime());
        formData.setPersonalTime(dateTime.plusDays(1).toDate());
        formService.update(formData);
        return new JSONBean("0", "ok");
    }

    /**
     * 为了减少前端访问接口的数量
     * 该接口：查看当前是否具有权限， 若有权限则删除   无权限则告知前端
     *
     * @param formData
     * @return
     */
    @RequestMapping("/delete/file/data/pid")
    @ResponseBody
    public JSONBean deleteFileAndDataBaseByPID(@RequestBody FormData formData, HttpSession session) {
        String msg = "", data = "";

        // 获取对应用户Session中保存的用户ID
        String accessToken = formData.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        formData.setuID(uId);

        List<FormData> list = formService.queryBeBean2(formData);
        try {
            if (list.size() > 0) {
                // 表示有权限
                FormData currData = list.get(0);
                if (!StringUtils.isEmpty(currData.getFilepath())) {
                    // 删除文件
                    String logoRealPath = session.getServletContext().getRealPath(currData.getFilepath());
                    File file = new File(logoRealPath);
                    if (file.exists()) {
                        file.delete();
                    }
                }
                formService.delete(formData);
                msg = "0";
                data = "ok";
            } else {
                // 表示暂无权限
                msg = "error";
                data = "none";
            }
        } catch (Exception r) {
            // 服务器内部错误   排查是否文件删除有问题
            msg = "error";
            data = "error";
        }

        return new JSONBean(msg, data);
    }

}
