package com.ingta.si.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ingta.si.util.InitParams;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统初始化监听器
 *
 */
public class SystemInitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(SystemInitListener.class);
    private static final String BASE_PATH = "BasePath";// 根路径
    private static ServletContext sc;

    public static ServletContext getServeletContext() {
        return sc;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sc = sce.getServletContext();
        initParams(sce.getServletContext());
    }

    public void initParams(ServletContext sce) {
        sce.setAttribute(BASE_PATH, sce.getContextPath());
        InitParams.INSTANCE.getInstance();// 初始化系统变量

        logger.info("system init success");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
