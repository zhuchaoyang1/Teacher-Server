package cn.usts.controller;

import cn.usts.pojo.SysService;
import cn.usts.service.SysServiceSer;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/8/1 23:34
 */

@Controller
@RequestMapping("/sys")
public class SysServiceController {

    @Resource
    private SysServiceSer sysServiceSer;

    @RequestMapping("/save")
    @ResponseBody
    public JSONBean saveSysService(@RequestBody SysService sysService){
        int uId = 0;
        // 获取对应用户Session中保存的用户ID
        if(!sysService.isSwitchs()){
            String accessToken = sysService.getUserToken();
            SessionContext sessionContext = SessionContext.getInstance();
            HttpSession userSession = sessionContext.getSession(accessToken);
            uId = Integer.parseInt(String.valueOf(userSession.getAttribute("u_id")));
        }

        sysService.setuId(uId);
        sysServiceSer.save(sysService);

        return new JSONBean("success",null);
    }

}
