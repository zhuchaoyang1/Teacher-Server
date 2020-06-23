package cn.usts.controller;

import cn.usts.pojo.Templete;
import cn.usts.service.TempleteService;
import cn.usts.util.JSONBean;
import com.sun.deploy.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/templetes")
public class TempleteController {

    @Autowired
    private TempleteService templeteService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public JSONBean queryAll() {
        return new JSONBean("0", templeteService.queryAll());
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONBean update(@RequestBody Templete templete, HttpSession session) {
        // 根据ID查询到以前的文件 并 删除  防止流浪文件的出现占用硬盘大小
        Templete fromData = templeteService.queryById(templete);
        if (fromData.getPath() != null && !StringUtils.isEmpty(fromData.getPath())) {
            String logoRealPath = session.getServletContext().getRealPath(fromData.getPath());
            File file = new File(logoRealPath);
            if (file.exists()) {
                file.delete();
            }
        }

        templeteService.update(templete);
        return new JSONBean("0", null);
    }

    @RequestMapping("/down/template")
    @ResponseBody
    public JSONBean exportTemplate(@RequestBody Templete templete, HttpServletRequest request, HttpServletResponse response) {
        List<Templete> reslut = templeteService.queryByBean(templete);
        return new JSONBean("0", reslut);
    }

    @RequestMapping("/down/template/detail")
    public void download(@RequestBody Templete templete, HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getSession().getServletContext().getRealPath(templete.getPath());
            File file = new File(path);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                //设置文件下载头
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
                response.setHeader("content-disposition", "attachment;filename=" + templete.getFname());
                // 创建输出流
                OutputStream out = response.getOutputStream();
                // 创建缓冲区
                byte buffer[] = new byte[1024];
                int len = 0;
                //循环将输入流中的内容读取到缓冲区当中
                while ((len = fileInputStream.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                    out.flush();
                }
                //关闭文件输入流
                fileInputStream.close();
                // 关闭输出流
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/update/moudle/name")
    @ResponseBody
    public JSONBean updateMoudleFileName(@RequestBody Templete templete) {
        templeteService.updateMoudleFileName(templete);
        return new JSONBean("0", null);
    }


}
