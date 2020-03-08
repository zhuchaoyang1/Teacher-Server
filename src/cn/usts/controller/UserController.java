package cn.usts.controller;

import cn.usts.pojo.SysUser;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import cn.usts.util.enums.SysUserCheck;
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
        if (!StringUtils.isEmpty(this.validateData(sysUser))) {
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
        if (sysUser.getRole() == null) {
            // 专业为空
            return SysUserCheck.NO_ROLE_STR.getStr();
        }

        if (sysUser.getRole() == 0 ||
                sysUser.getRole() == 1 ||
                sysUser.getRole() == 4 ||
                sysUser.getRole() == 5) {
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
        if (sysUser.getRole() != 0 &&
                sysUser.getRole() != 1 &&
                sysUser.getRole() != 4 &&
                sysUser.getRole() != 5 &&
                StringUtils.isEmpty(sysUser.getMajor())) {
            stringBuilder.append(SysUserCheck.NO_MARJOR.getStr() + "，");
        }
        if (StringUtils.isEmpty(sysUser.getCollege())) {
            stringBuilder.append(SysUserCheck.NO_COLLEGE.getStr() + "，");
        }
        return stringBuilder.toString();
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
