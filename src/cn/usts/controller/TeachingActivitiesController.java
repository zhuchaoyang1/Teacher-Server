package cn.usts.controller;

import cn.usts.pojo.SysUser;
import cn.usts.pojo.TeachingActivities;
import cn.usts.service.TeachingActivitiesService;
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
@RequestMapping("/teachActivity")
public class TeachingActivitiesController {

    @Autowired
    private TeachingActivitiesService teachingActivitiesService;
    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public JSONBean save(@RequestBody TeachingActivities teachingActivities) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingActivities.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        teachingActivities.setUId(uId);
        // 对PageOrSize构造学院信息和专业信息
        SysUser user = userService.queryById(uId).get(0);

        teachingActivities.setCollege(user.getCollege());
        teachingActivities.setMarjor(user.getMajor());
        teachingActivities.setAttentPeople(String.join(",", teachingActivities.getAttendPersons()));

        return new JSONBean("0", teachingActivitiesService.save(teachingActivities));
    }

    /**
     * 根据自己的学院或者专业来选择查看那些数据
     *
     * @param teachingActivities
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public JSONBean query(@RequestBody TeachingActivities teachingActivities) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingActivities.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        teachingActivities.setUId(uId);
        // 对PageOrSize构造学院信息和专业信息
        SysUser user = userService.queryById(uId).get(0);

        teachingActivities.setCollege(user.getCollege());
        teachingActivities.setMarjor(user.getMajor());

        Map<String, Object> map = new HashMap<>();

        map.put("count", teachingActivitiesService.queryCount(teachingActivities));
        map.put("list", teachingActivitiesService.queryByBean(teachingActivities));

        return new JSONBean("0", map);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONBean delete(@RequestBody TeachingActivities teachingActivities, HttpSession session) {
        String msg = "", data = "";

        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingActivities.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uSubmitId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        if (uSubmitId != teachingActivities.getUId()) {
            // 没有权限删除
            return new JSONBean("error", "NoAUth");
        }

        // 删除文件
        String logoRealPath = session.getServletContext().getRealPath(teachingActivities.getFilepath());
        File file = new File(logoRealPath);
        if (file.exists()) {
            file.delete();
        }

        // 删除数据库记录
        teachingActivitiesService.delete(teachingActivities);
        return new JSONBean("0", "success");
    }

    @RequestMapping("/queryBy")
    @ResponseBody
    public JSONBean queryById(@RequestBody TeachingActivities teachingActivities) {
        return new JSONBean("0", teachingActivitiesService.queryById(teachingActivities.getId()));
    }

    @RequestMapping("/file/delete/relation")
    @ResponseBody
    public JSONBean deleteFileAndFileData(@RequestBody TeachingActivities teachingActivities, HttpSession session) {
        if (!StringUtils.isEmpty(teachingActivities.getFilepath())) {
            // 删除文件
            String realPath = session.getServletContext().getRealPath(teachingActivities.getFilepath());
            File file = new File(realPath);
            if (file.exists()) {
                // 文件存在  删除文件
                file.delete();
            }
        }
        // 删除关系
        teachingActivitiesService.updateFileInfo(teachingActivities);
        return new JSONBean("0", "success");
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONBean updateData(@RequestBody TeachingActivities teachingActivities) {
        teachingActivities.setAttentPeople(String.join(",", teachingActivities.getAttendPersons()));
        DateTime dateTime = new DateTime(teachingActivities.getDeteTime());
        teachingActivities.setDeteTime(dateTime.plusDays(1).toDate());
        teachingActivitiesService.update(teachingActivities);
        return new JSONBean("0", "success");
    }

    @RequestMapping("/pd")
    @ResponseBody
    public JSONBean pd(@RequestBody TeachingActivities teachingActivities) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = teachingActivities.getUserToken();
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(accessToken);
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        if (teachingActivities.getUId() == uId) {
            return new JSONBean("0", "success");
        }

        return new JSONBean("error", "NoAuth");
    }

}
