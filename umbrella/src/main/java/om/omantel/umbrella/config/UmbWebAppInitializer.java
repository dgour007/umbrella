/**
 * 
 */
package om.omantel.umbrella.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import om.omantel.umbrella.listener.SessionListener;

/**
 * @author Dhiraj Gour
 * @since 20 July 2017
 * @version 1.0
 * This class configures DispatcherServlet
 */

public class UmbWebAppInitializer 
		extends AbstractAnnotationConfigDispatcherServletInitializer 
		implements WebApplicationInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { UmbRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { UmbWebConfig.class };
	}
	
	//Tomcat
	/*@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		//Set Profiles
		servletContext.setInitParameter("spring.profiles.default", "test");
		servletContext.setInitParameter("spring.profiles.active", "test");
		//servletContext.setInitParameter("spring.profiles.active", "dev");
		//servletContext.setInitParameter("spring.profiles.active", "prod");
		//servletContext.setInitParameter("spring.profiles.active", "dev, testdb"); //Set multiple active profile
		
		// Set Session Timeout
		servletContext.addListener(new SessionListener());
		
		//Prevent using URL Parameters for Session Tracking
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
	}*/
	
	//Weblogic
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		servletContext.setInitParameter("spring.profiles.default", "prod");
		servletContext.setInitParameter("spring.profiles.active", "prod");
		
		// Create the 'root' Spring application context
	    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	    rootContext.register(UmbRootConfig.class);
	    
	    // Manage the lifecycle of the root application context
	    servletContext.addListener(new ContextLoaderListener(rootContext));

	    // Create the dispatcher servlet's Spring application context
	    AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
	    dispatcherServlet.register(UmbWebConfig.class);

	    // Register and map the dispatcher servlet
	    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
	    dispatcher.setLoadOnStartup(1);
	    dispatcher.addMapping("/");

	    // Register spring security FilterChain
	    FilterRegistration.Dynamic registration = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
	    EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
	    registration.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
	    
	    //Set Session Timeout
 		servletContext.addListener(new SessionListener());
 		
 		//Prevent using URL Parameters for Session Tracking
 		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
	}
}