/**
 * 
 */
package om.omantel.umbrella.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

import om.omantel.umbrella.bean.Messages;

/**
 * @author Dhiraj Gour
 * @since 18 July 2017
 * @version 1.0
 */
public interface LoginService {

	Messages getInvalidMessage (HttpServletRequest request, MessageSource messageSource);
	
	List<String> getSessionMessage (MessageSource messageSource);
}
