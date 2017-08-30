/**
 * 
 */
package om.omantel.umbrella.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import om.omantel.umbrella.bean.Menu;
import om.omantel.umbrella.bean.Messages;
import om.omantel.umbrella.bean.User;
import om.omantel.umbrella.security.LdapUserDetailsContextMapper;
import om.omantel.umbrella.service.LoginService;
import om.omantel.umbrella.util.Constants;

/**
 * @author Dhiraj Gour
 * @since 20 July 2017
 * @version 1.0
 */

@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger("umbrella");
	
	private LoginService loginService = null;
	private Messages messages = null;
	private MessageSource messageSource;
	
	@Autowired
	LdapUserDetailsContextMapper contextMapper;
	
	@Value("${themes}")
	String themeStr;
	
	@Autowired
	public LoginController (LoginService loginService, MessageSource messageSource) {
		this.loginService = loginService;
		this.messageSource = messageSource;
		messages = new Messages();
	}
	
	@RequestMapping(value= "/logon", method=RequestMethod.GET)
	public String showLoginPage () {
		return "tiles.login";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goToAuth () {
		return "redirect:/auth";
	}
	
	@RequestMapping(value="/auth",method=RequestMethod.GET)
	public String getUserDetails (HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String ipAddress = request.getHeader("X-Forwarded-For")!= null ? request.getHeader("X-Forwarded-For") : request.getRemoteAddr();
		
		User user = loginService.getUserDetails(contextMapper, ipAddress);
		
		logger.info("User trying to login is: '{}' with name: '{}' from the IP: '{}' with rolestatus {} ",
				user.getUserId(), user.getFullName(), ipAddress, user.getRoleId());
		
		if (user.getRoleId() != 0) {
			
			session.setAttribute(Constants.USER, user);
			return "redirect:/menu/"+user.getAppId();
			
		} else {
			return "redirect:/log_out/"+"norole";
		}
	}
	
	//@DevProfile
	/*@RequestMapping(value="/auth1",method=RequestMethod.GET)
	public String getUserDetails1 (HttpSession session) {
		
		User user = new User();
		user.setUserId("70003");
		user.setFullName("Mr. Dhiraj Gour");
		user.setTheme("dark");
		
		session.setAttribute(Constants.USER, user);
		
		return "redirect:/home";
	}*/
	
	@RequestMapping(value="/menu/{appId}",method=RequestMethod.GET)
	public String getMenu (@PathVariable int appId, HttpSession session) {
		
		User user = (User)session.getAttribute(Constants.USER);
		List<Menu> menuList = loginService.getUserMenu(user.getRoleId(), appId);
		session.setAttribute(Constants.MENU, menuList);
		
		logger.info("Fetched Menu of app: '{}' for the user: '{}'", appId, user.getUserId());
		
		return "redirect:/home";
	}
	
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String goToHome () {
		return "tiles.home";
	}
	
	@RequestMapping(value="/invalid",method=RequestMethod.GET)
	public String invalidCredentials (HttpServletRequest request, Model model) {
		
		messages.reset();
		messages = loginService.getInvalidMessage(request, messageSource);
		model.addAttribute(Constants.MESSAGES,messages);
		
		return "tiles.login";
	}
	
	@RequestMapping(value="/session",method=RequestMethod.GET)
	public String invalidSession (Model model) {
		
		messages.reset();
		messages.setHasError(true);
		messages.setErrors(loginService.getSessionMessage(messageSource));
		model.addAttribute(Constants.MESSAGES,messages);
		
		return "tiles.login";
	}
	
	@RequestMapping(value="/log_out/{reason}",method=RequestMethod.GET)
	public String logout (@PathVariable String reason, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    
	    if ("norole".equalsIgnoreCase(reason)) {
		    messages.reset();
			messages.setHasError(true);
			messages.setErrors(loginService.getNoRoleMessage(messageSource));
			model.addAttribute(Constants.MESSAGES,messages);
	    }
	    
		return "tiles.login";
	}
	
	@RequestMapping(value="/access_denied",method=RequestMethod.GET)
	public String accessDenied () {
		return "tiles.access.denied";
	}
	
	@RequestMapping(value="/theme/{thm}",method=RequestMethod.GET)
	public String changeTheme (@PathVariable String thm, HttpSession session) {
		
		if (themeStr.indexOf(thm) != -1) {
			User user = (User)session.getAttribute(Constants.USER);
			int cnt = loginService.updateTheme(user.getUserId(), thm);
			
			if (cnt > 0) {
				user.setTheme(thm);
				session.setAttribute(Constants.USER, user);
				
				logger.info("User: '{}' Selected Theme is: '{}'",user.getUserId(), thm);
			}
		}
		
		return "tiles.home";
	}
}