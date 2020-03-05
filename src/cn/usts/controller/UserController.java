package cn.usts.controller;

import cn.usts.pojo.SysUser;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/14 23:12
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 登录操作
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public JSONBean querySysUser(@RequestBody SysUser sysUser, HttpSession httpSession) {
        List<SysUser> all = userService.queryByBean(sysUser);
        String msg;
        String user_token = null;

        Map<String, String> map = new HashMap<String, String>();

        if (all.size() == 1) {
            // 登录成功
            msg = "success";
            int id = all.get(0).getId();
            httpSession.setAttribute("u_id", id);
            user_token = httpSession.getId();
            map.put("user_token", user_token);
        } else {
            // 登录失败
            msg = "fail";
        }

        return new JSONBean(msg, map);
    }

    @RequestMapping("/getAllStaff")
    @ResponseBody
    public JSONBean returnAllTeachers() {
        return new JSONBean("success", userService.queryAllStaffTeachers());
    }

    /**
     * 单个数据的导入
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/single/import")
    @ResponseBody
    public JSONBean insertSingle(@RequestBody SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getRealName()) ||
                StringUtils.isEmpty(sysUser.getCollege()) ||
                StringUtils.isEmpty(sysUser.getMajor()) ||
                StringUtils.isEmpty(sysUser.getRole()) ||
                StringUtils.isEmpty(sysUser.getPassword()) ||
                StringUtils.isEmpty(sysUser.getPhone()) ||
                StringUtils.isEmpty(sysUser.getName())) {
            return new JSONBean("error", "表单数据未填写完整");
        }
        // 严重是否重复导入
        if (CollectionUtils.isEmpty(userService.queryByRealNameAndUsernameAndCollege(sysUser))) {
            userService.save(sysUser);
            return new JSONBean("success", sysUser);
        } else {
            return new JSONBean("error", "该教师已存在，不可重复导入");
        }
    }

    /**
     * 用于 系主任 上传 教学活动记录表时的  选择参加人员所用
     * 选择系主任当前专业下的所有老师
     * 选择系主任所在当前学院的院长
     */
    @RequestMapping("/choose/marjor/person")
    @ResponseBody
    public JSONBean chooseSysUser(@RequestBody SysUser sysUser) {
        // 获取对应用户Session中保存的用户ID
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(sysUser.getUserToken());
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        SysUser currUser = userService.queryById(uId).get(0);

        sysUser.setMajor(currUser.getMajor());
        sysUser.setCollege(currUser.getCollege());

        return new JSONBean("0", userService.queryAllCollegeOrMarjor(currUser));
    }

    @RequestMapping("/queryByCollege")
    @ResponseBody
    public JSONBean queryByCollege(@RequestBody SysUser sysUser) {
        // 获取对应用户Session中保存的用户ID
        SessionContext sessionContext = SessionContext.getInstance();
        HttpSession userSession = sessionContext.getSession(sysUser.getUserToken());
        int uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));

        SysUser currUser = userService.queryById(uId).get(0);

        sysUser.setCollege(currUser.getCollege());

        return new JSONBean("0", userService.queryByCollege(sysUser));
    }

}
