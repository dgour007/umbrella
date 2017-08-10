/**
 * 
 */
package om.omantel.umbrella.config;

import java.util.EnumSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import om.omantel.umbrella.listener.SessionListener;

/**
 * @author Dhiraj Gour
 * @since 20 July 2017
 * @version 1.0
 * This class configures DispatcherServlet
 */

public class UmbWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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
	
	@Override
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
	}
}