package cn.usts.controller.util;

import cn.usts.service.FormService;
import cn.usts.util.JSONBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传复用Controller
 *
 * @Author: ${朱朝阳}
 * @Date: 2019/7/16 12:49
 */
@Controller
@RequestMapping("/file")
public class FileUpload {

    @Autowired
    private FormService formService;

    @RequestMapping("/fileUpload")
    @ResponseBody
    public JSONBean uploadFile(@RequestParam("file") MultipartFile oldFile, String uploadPath, HttpSession session) {

        String originalFileName = oldFile.getOriginalFilename();

        String newFileName = UUID.randomUUID() + originalFileName;

        String logoRealPath = session.getServletContext().getRealPath(uploadPath);

        File newFile = new File(logoRealPath + "/" + newFileName);

        if (!newFile.exists()) {
            newFile.mkdirs();
        } else {
            return new JSONBean("error", "该文件已存在，系统已为您开辟新的路径，请重新上传！");
        }

        try {
            oldFile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONBean("error", null);
        }

        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("filePathname", uploadPath + "/" + newFileName);

        return new JSONBean("success", map);
    }

    /**
     * 纯粹删除文件
     *
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/deleteFile")
    @ResponseBody
    public JSONBean deleteFile(@RequestBody Map<String, String> map, HttpSession session) {
        String logoRealPath = session.getServletContext().getRealPath(map.get("filePath"));

        File file = new File(logoRealPath);
        if (file.exists()) {
            file.delete();
            return new JSONBean("success", "文件删除成功");
        }
        return new JSONBean("success", "删除失败，没有该文件！");
    }

    /**
     * 删除文件以及删除当前表格的关系
     *
     * @param map
     * @param session
     * @return
     */
    @RequestMapping("/delete/relation")
    @ResponseBody
    public JSONBean deleteFileAndRelation(@RequestBody Map<String, String> map, HttpSession session) {
        String logoRealPath = session.getServletContext().getRealPath(map.get("filePath"));

        File file = new File(logoRealPath);
        if (file.exists()) {
            file.delete();
            formService.deleteRelation(map);
            return new JSONBean("success", "文件删除成功");
        }
        return new JSONBean("error", "删除失败，没有该文件！");
    }


}
