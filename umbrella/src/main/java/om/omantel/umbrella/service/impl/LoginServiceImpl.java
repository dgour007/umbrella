/**
 * 
 */
package om.omantel.umbrella.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import om.omantel.umbrella.bean.Menu;
import om.omantel.umbrella.bean.Messages;
import om.omantel.umbrella.bean.User;
import om.omantel.umbrella.dao.LoginDao;
import om.omantel.umbrella.security.LdapUser;
import om.omantel.umbrella.security.LdapUserDetailsContextMapper;
import om.omantel.umbrella.service.LoginService;
import om.omantel.umbrella.util.Constants;

/**
 * @author Dhiraj Gour
 * @since 18 July 2017
 * @version 1.0
 */

@Component
public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;
	
	@Autowired
	public LoginServiceImpl (LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	@Override
	public Messages getInvalidMessage (HttpServletRequest request, MessageSource messageSource) {
		
		Messages msg = new Messages();
		Exception e = (Exception)request.getSession().getAttribute(Constants.SPRING_SECURITY_LAST_EXCEPTION);
		String error = null;
		List<String> errorMessages = new ArrayList<String>();
		
		if ((e != null) 
				&& (e.toString().indexOf("Maximum sessions") != -1)) {
			
			errorMessages.add(messageSource.getMessage(Constants.WR_101, null,Locale.ENGLISH));
			msg.setHasWarning(true);
			msg.setWarnings(errorMessages);
			return msg;
			
		} else if ((e != null) 
				&& (e.toString().indexOf("Bad credential") != -1)) {
			
			errorMessages.add(messageSource.getMessage(Constants.ER_101, null,Locale.ENGLISH));
			
		} else {
			error = (String)request.getSession().getAttribute(Constants.LOGIN_ERROR);
			
			if (error != null) {
				errorMessages.add(messageSource.getMessage(error, null,Locale.ENGLISH));
			} else {
				errorMessages.add(messageSource.getMessage(Constants.ER_103, null,Locale.ENGLISH));
			}
		}
		
		msg.setHasError(true);
		msg.setErrors(errorMessages);
		return msg;
	}

	@Override
	public List<String> getSessionMessage(MessageSource messageSource) {
		List<String> errorMessages = new ArrayList<String>(0);
		errorMessages.add(messageSource.getMessage(Constants.ER_111, null,Locale.ENGLISH));
		return errorMessages;
	}

	@Override
	public User getUserDetails(LdapUserDetailsContextMapper contextMapper, String ipAddress) {
		
		LdapUser ldapUser = contextMapper.getUserDetails();
		
		User user = loginDao.getUserDetails(ldapUser.getUsername(), ipAddress);
		
		user.setUserId(ldapUser.getUsername());
		user.setFullName(ldapUser.getInitials()+" "+ldapUser.getFullName());
		user.setDepartment(ldapUser.getDepartment());
		user.seteMailAddress(ldapUser.geteMailAddress());
		user.setInitials(ldapUser.getInitials());
		//user.setMobile(ldapUser.getMobile());
		
		return user;
	}

	@Override
	public List<Menu> getUserMenu(int roleId, int appId) {
		
		List<Menu> menuList = loginDao.getMenu(roleId, appId);
		List<Menu> titleMenuList = new ArrayList<>(0);
		
		for (Menu titleMenu : menuList) {
			if (titleMenu.getParentMenuId() == 0) {
				
				int titleMenuId = titleMenu.getMenuId();
				List<Menu> menuListLevel1 = new ArrayList<>(0);
				
				for (Menu menuLevel1 : menuList) {
					
					if (menuLevel1.getParentMenuId() == titleMenuId) {
						
						int menuIdLevel1 = menuLevel1.getMenuId();
						List<Menu> menuListLevel2 = new ArrayList<>(0);
						
						for (Menu menuLevel2 : menuList) {
							
							if (menuLevel2.getParentMenuId() == menuIdLevel1) {
								
								int menuIdLevel2 = menuLevel2.getMenuId();
								List<Menu> menuListLevel3 = new ArrayList<>(0);
								
								for (Menu menuLevel3 : menuList) {
									
									if (menuLevel3.getParentMenuId() == menuIdLevel2) {
										menuListLevel3.add(menuLevel3);
										//menuList.remove(menuLevel3);
									}
								}
								menuLevel2.setChildMenu(menuListLevel3);
								menuListLevel2.add(menuLevel2);
								//menuList.remove(menuLevel2);
							}
						}
						menuLevel1.setChildMenu(menuListLevel2);
						menuListLevel1.add(menuLevel1);
						//menuList.remove(menuLevel1);
					}
				}
				titleMenu.setChildMenu(menuListLevel1);
				titleMenuList.add(titleMenu);
				//menuList.remove(titleMenu);
			}
		}
		
		return titleMenuList;
	}

	@Override
	public int updateTheme(String userId, String theme) {
		return loginDao.updateTheme(userId, theme);
	}

	@Override
	public List<String> getNoRoleMessage(MessageSource messageSource) {
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add(messageSource.getMessage(Constants.ER_102, null, Locale.ENGLISH));
		return errorMessages;
	}
}
