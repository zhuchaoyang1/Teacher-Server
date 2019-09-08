package cn.usts.util.session;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/15 1:06
 */

public class SessionContext {

    private static SessionContext instance;
    private HashMap<String,HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap<String,HttpSession>();
    }

    /**
     * 单例
     * @return
     */
    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }

}
