package cn.usts.controller;

import cn.usts.pojo.SysUser;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 密码服务   包括重置密码
 */
@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private UserService userService;

    /**
     * @param type    1. find  查询   2.update  更新
     * @param sysUser
     * @return
     */
    @RequestMapping("/update/password")
    @ResponseBody
    public JSONBean resetPassword(@RequestParam String type, @RequestBody SysUser sysUser) {
        // 1. 验证用户是否存在
        if (type.equals("find")) {
            List<SysUser> sysUsersFromDataBase = userService.queryByRealNameAndSoOn(sysUser);
            return new JSONBean("0", sysUsersFromDataBase.size() > 0 ? sysUsersFromDataBase.get(0).getId() : "error");
        }
        // 2. 重置密码
        if (type.equals("update")) {
            // 需要传入ID 和 新密码
            userService.updatePwd(sysUser);
            return new JSONBean("0", "success");
        }
        return new JSONBean("0", "操作失败");
    }

}
