package com.wave.utils;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Useful for getting the Spring' ApplicationContext instance. <br/> <br/>
 * This instance can be either
 * <ul>
 * 	<li>ApplicationContext loaded at the time of web application startup (by context listener)</li>
 * 	<li>custom ApplicationContext by reading the applicationContext.xml again</li>
 * 	<li>ApplicationContext bound to the given ServletContext</li>
 *  <li></li>
 * </ul>
 * 
 * @author ManiKanta
 */
public class SpringUtil {

	/**
	 * Spring' ApplicationContext build using
	 * the applicationContext.xml found in the class path
	 */
	public final static ApplicationContext CP_XML_APP_CONTEXT =
			new ClassPathXmlApplicationContext("applicationContext.xml");


	/**
	 * Web application' loaded Spring ApplicationContext
	 */
	public final static ApplicationContext WEB_APP_CONTEXT = getWebApplicationContext();


	/**
	 * Returns the application context loaded by the webapp loader
	 * using the listener
	 * @return
	 */
	public static ApplicationContext getWebApplicationContext() {
		return ContextLoader.getCurrentWebApplicationContext();
	}


	/**
	 * Returns the Spring' ApplicationContext bound to the given ServletContext
	 * @param context
	 * @return
	 */
	public ApplicationContext getApplicationContext(ServletContext context) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}

}
