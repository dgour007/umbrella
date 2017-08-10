/**
 * 
 */
package om.omantel.umbrella.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import om.omantel.umbrella.util.Constants;

/**
 * @author Dhiraj Gour
 * @date 05Aug 2017
 *
 */
@Aspect
@Component
public class BreadcrumbAspect {

	/*@Pointcut("execution(** om.omantel.pymt.controller.*.*(..))")
	//@Pointcut("execution(* *.*(..))")
	public void pageChange() {}
	
	@Before("pageChange()")
	public void arrangeBreadCrumbs() {
		System.out.println("Arranging Bread Crumbs");
	}*/
	
	@Autowired
	Environment env;
	
	@Pointcut(
	        "within(@org.springframework.stereotype.Controller *) && " +
	        "@annotation(requestMapping) && " +
	        "execution(* *(..))"
	    )
    public void pageChange (RequestMapping requestMapping) {}

    @Before("pageChange (requestMapping)")
    public void arrangeBreadCrumbs (RequestMapping requestMapping) {
        String mapping = requestMapping.value()[0];
        String heading = env.getProperty(mapping+".heading");
        String crumbStr = env.getProperty(mapping+".crumb");
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        
        if (session != null && heading != null) {
        	session.removeAttribute(Constants.HEADING);
        	session.setAttribute(Constants.HEADING, heading);
        	
        	session.removeAttribute(Constants.CRUMB);
        	
        	if (crumbStr != null && !"".equalsIgnoreCase(crumbStr)) {
        		String[] crumbs = crumbStr.split("/");
        		session.setAttribute(Constants.CRUMB, crumbs);
        	}
        }
    }
}
