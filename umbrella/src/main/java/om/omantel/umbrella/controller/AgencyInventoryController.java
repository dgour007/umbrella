/**
 * 
 */
package om.omantel.umbrella.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import om.omantel.umbrella.bean.Messages;
import om.omantel.umbrella.bean.User;
import om.omantel.umbrella.form.AgencyInventoryReportForm;
import om.omantel.umbrella.service.AgencyInventoryService;
import om.omantel.umbrella.util.Commons;
import om.omantel.umbrella.util.Constants;

/**
 * @author Dhiraj Gour
 * @date 16 August 2017
 *
 */
@Controller
@RequestMapping("/agency/inv")
public class AgencyInventoryController {
	
	private static Logger logger = LoggerFactory.getLogger("umbrella");
	
	private AgencyInventoryService agencyInventoryService = null;
	private Messages messages = null;
	private MessageSource messageSource;
	
	@Autowired
	public AgencyInventoryController (AgencyInventoryService agencyInventoryService, MessageSource messageSource) {
		this.agencyInventoryService = agencyInventoryService;
		this.messageSource = messageSource;
		messages = new Messages();
	}
	
	@RequestMapping(value="/summary", method=RequestMethod.GET)
	public String goToSummaryPage (HttpServletRequest request, Model model) {
		
		User user= (User)request.getSession().getAttribute(Constants.USER);
		AgencyInventoryReportForm form = agencyInventoryService.getFormDetails(user.getIpAddress(), user.getAuthority());
		
		if (form.getErrorCode()!=null) {
			messages.setHasError(true);
			messages.setErrors(Commons.getErrorMessage(messageSource, form.getErrorCode(), Locale.ENGLISH));
			model.addAttribute(Constants.MESSAGES,messages);
		} else if (form.getWarningCode()!=null) {
			messages.setHasWarning(true);
			messages.setWarnings(Commons.getWarningMessage(messageSource, form.getWarningCode(), Locale.ENGLISH));
			model.addAttribute(Constants.MESSAGES,messages);
		}
		
		logger.info("Forwarding user: '{}' to agency inventory summary report", user.getUserId());
		
		model.addAttribute("agencyInvForm",form);
		
		return "tiles.agency.inv.summary";
	}
	
	@RequestMapping(value="/summarydata", method=RequestMethod.POST)
	public String getSummary (@ModelAttribute("agencyInvForm") AgencyInventoryReportForm form, Model model) {
		
		messages = agencyInventoryService.validateForm(form, messageSource);
		
		if (messages.isHasError() || messages.isHasWarning()) {
			 
			model.addAttribute(Constants.MESSAGES,messages);
			logger.info("Agency inventory summary form validation error: {} and warning: {}", 
					messages.isHasError(), messages.isHasWarning());
			
		} else {
		
			form = agencyInventoryService.getSummaryData(form);
			
			if (form.getErrorCode()!=null) {
				messages.setHasError(true);
				messages.setErrors(Commons.getErrorMessage(messageSource, form.getErrorCode(), Locale.ENGLISH));
				model.addAttribute(Constants.MESSAGES,messages);
			} else if (form.getWarningCode()!=null) {
				messages.setHasWarning(true);
				messages.setWarnings(Commons.getWarningMessage(messageSource, form.getWarningCode(), Locale.ENGLISH));
				model.addAttribute(Constants.MESSAGES,messages);
			}
			
			logger.info("Agency inventory summary data size: {}", 
					form.getAgencyInvSummary()!=null ? form.getAgencyInvSummary().size() : "NULL");
		}
		
		model.addAttribute("agencyInvForm",form);
		
		return "tiles.agency.inv.summary";
	}
}
