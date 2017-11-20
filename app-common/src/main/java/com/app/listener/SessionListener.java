package com.app.listener;

import com.app.utils.SessionContext;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session监听器
 * com.app.listener
 * @author zhujiamin
 * @date 2017/8/1
 */
public class SessionListener implements HttpSessionAttributeListener,HttpSessionListener {

    private SessionContext sessionContext= SessionContext.getInstance();

    /**
     *在session中添加属性出发此操作
     * @param event
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    /**
     * 删除时出发此操作
     * @param event
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("session删除");
    }

    /**
     * session修改时出发此操作
     * @param event
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("session修改");

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionContext.addSession(se.getSession());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionContext.delSession(se.getSession());
    }
}
