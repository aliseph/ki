package com.ingta.framework.util.spring;

import java.util.Locale;
import org.springframework.context.ApplicationContext;

public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static String getMessage(String messageId) {
        return applicationContext.getMessage(messageId, null, null);
    }
}