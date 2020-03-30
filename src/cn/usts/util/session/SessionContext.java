package cn.usts.util.session;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/15 1:06
 */
@Slf4j
public class SessionContext {

    private static SessionContext instance;
    private HashMap<String, HttpSession> sessionMap;

    private SessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    /**
     * 单例
     *
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
            this.printAllSession();
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }

    public synchronized int getSessionSize() {
        return sessionMap.size();
    }

    public void printAllSession() {
        for (String sessionStrKey : sessionMap.keySet()) {
//            System.out.println("打印目前的回话数：" + sessionMap.get(sessionStrKey));
            log.info(sessionMap.get(sessionStrKey).toString());
        }
    }

    /**
     * 用于后台管理  通过postman清空所有Session
     */
    public void deleteAllSession() {
        for (String sessionStrKey : sessionMap.keySet()) {
            sessionMap.get(sessionStrKey).invalidate();
        }
    }

}
