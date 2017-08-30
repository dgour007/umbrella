/**
 * 
 */
package om.omantel.umbrella.service;

import org.springframework.context.MessageSource;

import om.omantel.umbrella.bean.Messages;
import om.omantel.umbrella.form.AgencyInventoryReportForm;

/**
 * @author Dhiraj Gour
 * @date 16 August 2017
 *
 */
public interface AgencyInventoryService {
	
	AgencyInventoryReportForm getFormDetails (String ipAddress, int authority);
	
	AgencyInventoryReportForm getSummaryData (AgencyInventoryReportForm form);
	
	Messages validateForm (AgencyInventoryReportForm form, MessageSource messageSource);
}
