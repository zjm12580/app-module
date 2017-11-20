package com.app.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * com.app.utils
 *
 * @param ${param}
 * @author zhujiamin
 * @date 2017/8/1
 */
public class SessionContext {

    private HttpSession httpSession;

    private HashMap sessionMap;

    private SessionContext() {
        sessionMap = new HashMap();
    }

    private static SessionContext sessionUtils = null;

    public static synchronized SessionContext getInstance() {
        if (sessionUtils == null) {
            sessionUtils = new SessionContext();
        }
        return sessionUtils;
    }

    /**
     * 添加所有的session到sessionMap中
     * @param httpSession
     */
    public synchronized void addSession(HttpSession httpSession) {
        if (httpSession != null) {
            sessionMap.put(httpSession.getId(), httpSession);
        }
        this.httpSession = httpSession;

    }

    public void delSession(HttpSession httpSession) {
        if (httpSession != null) {
            sessionMap.remove(httpSession.getId());
        }
    }

}
