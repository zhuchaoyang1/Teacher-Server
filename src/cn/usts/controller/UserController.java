package cn.usts.controller;

import cn.usts.pojo.SysUser;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

        Map<String,String> map = new HashMap<String, String>();

        if (all.size() == 1) {
            // 登录成功
            msg = "success";
            int id = all.get(0).getId();
            httpSession.setAttribute("u_id", id);
            user_token = httpSession.getId();
            map.put("user_token",user_token);
        } else {
            // 登录失败
            msg = "fail";
        }

        return new JSONBean(msg, map);
    }

}
