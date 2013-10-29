package com.ingta.framework.util.spring;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {
	private static final Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event
				.getServletContext());
		SpringContextUtil.setApplicationContext(context);
		logger.info("spring init over");
	}
}