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

import om.omantel.umbrella.bean.Messages;
import om.omantel.umbrella.dao.LoginDao;
import om.omantel.umbrella.service.LoginService;
import om.omantel.umbrella.util.Constants;

/**
 * @author Dhiraj Gour
 * @since 18 July 2017
 * @version 1.0
 */

@Component
public class LoginServiceImpl implements LoginService {

	/*private LoginDao loginDao;
	
	@Autowired
	public LoginServiceImpl (LoginDao loginDao) {
		this.loginDao = loginDao;
	}*/
	
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
}
