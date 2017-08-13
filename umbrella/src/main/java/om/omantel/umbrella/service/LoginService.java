/**
 * 
 */
package om.omantel.umbrella.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

import om.omantel.umbrella.bean.Menu;
import om.omantel.umbrella.bean.Messages;
import om.omantel.umbrella.bean.User;
import om.omantel.umbrella.security.LdapUserDetailsContextMapper;

/**
 * @author Dhiraj Gour
 * @since 18 July 2017
 * @version 1.0
 */
public interface LoginService {

	Messages getInvalidMessage (HttpServletRequest request, MessageSource messageSource);
	
	List<String> getSessionMessage (MessageSource messageSource);
	
	User getUserDetails (LdapUserDetailsContextMapper contextMapper, String ipAddress);

	List<String> getNoRoleMessage (MessageSource messageSource);
	
	List<Menu> getUserMenu (int roleId, int appId);
	
	int updateTheme (String userId, String theme);
}
