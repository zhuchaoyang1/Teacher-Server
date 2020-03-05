package cn.usts.controller;

import cn.usts.pojo.SysService;
import cn.usts.pojo.SysUser;
import cn.usts.service.SysServiceSer;
import cn.usts.service.UserService;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.swing.StringUIClientPropertyKey;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/8/1 23:34
 */

@Controller
@RequestMapping("/sys")
public class SysServiceController {

    @Resource
    private SysServiceSer sysServiceSer;
    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public JSONBean saveSysService(@RequestBody SysService sysService) {
        int uId = 0;
        // 获取对应用户Session中保存的用户ID
        if (!sysService.isSwitchs()) {
            String accessToken = sysService.getUserToken();
            SessionContext sessionContext = SessionContext.getInstance();
            HttpSession userSession = sessionContext.getSession(accessToken);
            uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));
        }

        sysService.setUId(uId);
        sysServiceSer.save(sysService);

        return new JSONBean("success", null);
    }

    @RequestMapping("/query")
    @ResponseBody
    public JSONBean queryAll(@RequestBody SysService sysService) {
        List<SysService> allSetrviceList = sysServiceSer.queryByBean(sysService);
        // 根据UID查询用户
        allSetrviceList.forEach(item -> {
            if (item.getUId() != 0) {
                item.setSysUser(
                        userService.queryById(
                                item.getUId()
                        ).get(0));
            }
        });

        return new JSONBean("0", new Object[]{
                allSetrviceList,
                sysServiceSer.getCountSize(null)}
        );
    }
}
