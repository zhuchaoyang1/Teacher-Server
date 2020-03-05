package cn.usts.util.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/15 1:10
 */

public class SessionListener implements HttpSessionListener {

    private SessionContext sessionContext = SessionContext.getInstance();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("============= 会话创建 ===  当前会话数：" + sessionContext.getSessionSize());
        sessionContext.addSession(session);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        sessionContext.delSession(session);
        System.out.println("============= 会话销毁 === 剩下会话数：" + sessionContext.getSessionSize());
    }
}
