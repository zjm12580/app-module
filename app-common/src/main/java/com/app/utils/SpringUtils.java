package com.app.utils;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * com.app.utils
 *
 * @author zhujiamin
 * @date 2017/8/1
 */
public class SpringUtils {
    private static ApplicationContext factory;

    private SpringUtils() {
        factory = new ClassPathXmlApplicationContext("ServiceBean.xml");
    }

    private static SpringUtils springUtils = null;

    private static synchronized SpringUtils getInstance() {
        if (springUtils == null) {
            springUtils = new SpringUtils();
        }
        return springUtils;
    }

    private static Object getBean(String beanName) {
        return factory.getBean(beanName);
    }
}
