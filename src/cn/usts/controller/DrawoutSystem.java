package cn.usts.controller;

import cn.usts.pojo.vo.FormToken;
import cn.usts.util.JSONBean;
import cn.usts.util.session.SessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 退出系统
 * 删除Session减轻服务器压力
 *
 * @Author: ${朱朝阳}
 * @Date: 2019/7/19 0:10
 */

@Controller
@RequestMapping("/out")
public class DrawoutSystem {

    @RequestMapping("/system")
    @ResponseBody
    public void outSystem(@RequestBody FormToken formToken) {
        // 获取对应用户Session中保存的用户ID
        String accessToken = formToken.getUserToken();
        if (accessToken != null) {
            SessionContext sessionContext = SessionContext.getInstance();
            HttpSession session = sessionContext.getSession(accessToken);
            if (session != null) {
                sessionContext.delSession(session);
                // 删除Session
                session.invalidate();
            }
        }
    }

    @RequestMapping("/clear/all/sessions")
    @ResponseBody
    public JSONBean deleteAllSession() {
        SessionContext sessionContext = SessionContext.getInstance();
        sessionContext.deleteAllSession();
        return new JSONBean("0", "success");
    }


}
