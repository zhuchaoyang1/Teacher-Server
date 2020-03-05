package cn.usts.controller;

import cn.usts.pojo.SysUser;
import cn.usts.pojo.TeachingActivities;
import cn.usts.pojo.TeachingTask;
import cn.usts.service.TeachingTaskService;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TeachingTaskController {

    @Autowired
    private TeachingTaskService teachingTaskService;
    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public JSONBean save(@RequestBody TeachingTask teachingTask) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingTask.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        SysUser user = userService.queryById(uId).get(0);

        if (user != null) {
            teachingTask.setUId(uId);
            teachingTask.setCollege(user.getCollege());
            teachingTask.setMarjor(user.getMajor());
            teachingTaskService.save(teachingTask);
            return new JSONBean("0", "success");
        } else {
            return new JSONBean("-1", "NoAuth");
        }

    }


    @RequestMapping("/query")
    @ResponseBody
    public JSONBean query(@RequestBody TeachingTask teachingTask) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingTask.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        teachingTask.setUId(uId);
        // 对PageOrSize构造学院信息和专业信息
        SysUser user = userService.queryById(uId).get(0);

        teachingTask.setCollege(user.getCollege());
        teachingTask.setMarjor(user.getMajor());

        Map<String, Object> map = new HashMap<>();

        map.put("count", teachingTaskService.getCount(teachingTask));
        map.put("list", teachingTaskService.queryAll(teachingTask));

        return new JSONBean("0", map);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONBean delete(@RequestBody TeachingTask teachingTask, HttpSession session) {
        String msg = "", data = "";

        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingTask.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uSubmitId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        if (uSubmitId != teachingTask.getUId()) {
            // 没有权限删除
            return new JSONBean("error", "NoAUth");
        }

        // 删除文件
        String logoRealPath = session.getServletContext().getRealPath(teachingTask.getFilepath());
        File file = new File(logoRealPath);
        if (file.exists()) {
            file.delete();
        }

        // 删除数据库记录
        teachingTaskService.delete(teachingTask);
        return new JSONBean("0", "success");
    }

    @RequestMapping("/queryBy")
    @ResponseBody
    public JSONBean queryById(@RequestBody TeachingTask teachingTask) {
        return new JSONBean("0", teachingTaskService.queryById(teachingTask.getId()));
    }

    @RequestMapping("/pd")
    @ResponseBody
    public JSONBean pd(@RequestBody TeachingTask teachingTask) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingTask.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        if (teachingTask.getUId() == uId) {
            return new JSONBean("0", "success");
        }

        return new JSONBean("error", "NoAuth");
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONBean updateData(@RequestBody TeachingTask teachingTask) {
        teachingTaskService.update(teachingTask);
        return new JSONBean("0", "success");
    }

    @RequestMapping("/file/delete/relation")
    @ResponseBody
    public JSONBean deleteFileAndFileData(@RequestBody TeachingTask teachingTask, HttpSession session) {
        if (!StringUtils.isEmpty(teachingTask.getFilepath())) {
            // 删除文件
            String realPath = session.getServletContext().getRealPath(teachingTask.getFilepath());
            File file = new File(realPath);
            if (file.exists()) {
                // 文件存在  删除文件
                file.delete();
            }
        }
        // 删除关系
        teachingTaskService.updateFile(teachingTask);
        return new JSONBean("0", "success");
    }

}
