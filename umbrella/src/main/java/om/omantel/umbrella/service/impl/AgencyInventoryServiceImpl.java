/**
 * 
 */
package om.omantel.umbrella.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import om.omantel.umbrella.bean.AgencyInventoryData;
import om.omantel.umbrella.bean.Messages;
import om.omantel.umbrella.dao.AgencyInventoryDao;
import om.omantel.umbrella.form.AgencyInventoryReportForm;
import om.omantel.umbrella.service.AgencyInventoryService;
import om.omantel.umbrella.util.Commons;
import om.omantel.umbrella.util.Constants;

/**
 * @author Dhiraj Gour
 * @date 16 August 2017
 *
 */
@Component
public class AgencyInventoryServiceImpl implements AgencyInventoryService {

	private static Logger logger = LoggerFactory.getLogger("umbrella");

	private AgencyInventoryDao agencyInventoryDao;

	@Autowired
	public AgencyInventoryServiceImpl(AgencyInventoryDao loginDao) {
		this.agencyInventoryDao = loginDao;
	}

	@Override
	public AgencyInventoryReportForm getFormDetails(String ipAddress, int authority) {
		AgencyInventoryReportForm form = new AgencyInventoryReportForm();

		try {
			form.setCounterList(agencyInventoryDao.getCounterList(ipAddress, authority));
			form.setItemList(agencyInventoryDao.getAgencyItemList());
			form.setAgencyList(agencyInventoryDao.getAgencyList(ipAddress, authority));

			logger.debug("Agency Inv Summary Report: Counter List size: {}", form.getCounterList().size());
			logger.debug("Agency Inv Summary Report: Agency List size: {}", form.getAgencyList().size());
			logger.debug("Agency Inv Summary Report: Item List size: {}", form.getItemList().size());

			if (form.getCounterList().size() == 0) {
				form.setWarningCode(Constants.WR_EMPTY_COUNTERS);
			} else if (form.getAgencyList().size() == 0) {
				form.setWarningCode(Constants.WR_EMPTY_AGENCY);
			} else if (form.getItemList().size() == 0) {
				form.setWarningCode(Constants.WR_EMPTY_ITEMS);
			}

		} catch (Exception ex) {
			form.setErrorCode(Constants.ER_TECH_PROB);
			logger.error("Exception class: '{}' Method: '{}' message: '{}'", this.getClass().getName(),
					"getFormDetails", ex.getMessage());
		}
		return form;
	}

	@Override
	public AgencyInventoryReportForm getSummaryData(AgencyInventoryReportForm form) {
		try {

			List<AgencyInventoryData> dataList = agencyInventoryDao.getAgencyInvSummaryData(form.getItemCodes(),
					form.getCounterIds(), form.getAgencyIds(), form.getStartDate(), form.getEndDate());

			if (dataList == null || dataList.size() == 0) {
				form.setWarningCode(Constants.WR_NO_DATA);
			} else {
				form.setAgencyInvSummary(dataList);
			}

		} catch (Exception ex) {
			form.setErrorCode(Constants.ER_TECH_PROB);
			logger.error("Exception class: '{}' Method: '{}' message: '{}'", this.getClass().getName(),
					"getSummaryData", ex.getMessage());
		}
		return form;
	}

	@Override
	public Messages validateForm(AgencyInventoryReportForm form, MessageSource messageSource) {
		Messages msg = new Messages();
		List<String> errorMessages = new ArrayList<String>();
		List<String> warningMessages = new ArrayList<String>();

		try {

			logger.debug("Selected Counters size {}", form.getCounterIds().size());
			logger.debug("Selected Agencies size {}", form.getAgencyIds().size());
			logger.debug("Selected Items size {}", form.getItemCodes().size());

			if (form.getCounterIds() == null || form.getCounterIds().size() == 0) {
				warningMessages.add(messageSource.getMessage(Constants.WR_SELECT_COUNTER, null, Locale.ENGLISH));
			} 
			if (form.getItemCodes() == null || form.getItemCodes().size() == 0) {
				warningMessages.add(messageSource.getMessage(Constants.WR_SELECT_ITEM, null, Locale.ENGLISH));
			}
			if (form.getAgencyIds() == null || form.getAgencyIds().size() == 0) {
				warningMessages.add(messageSource.getMessage(Constants.WR_SELECT_AGENCY, null, Locale.ENGLISH));
			} 
			if (form.getStartDate() == null || !Commons.isValidDate(form.getStartDate(), Constants.YYYY_MM_DD)) {
				warningMessages.add(messageSource.getMessage(Constants.WR_SELECT_START_DATE, null, Locale.ENGLISH));
			} 
			if (form.getEndDate() == null || !Commons.isValidDate(form.getEndDate(), Constants.YYYY_MM_DD)) {
				warningMessages.add(messageSource.getMessage(Constants.WR_SELECT_END_DATE, null, Locale.ENGLISH));
			} else if (!Commons.checkDateRange(form.getStartDate(), form.getEndDate(), Constants.YYYY_MM_DD)) {
				errorMessages.add(messageSource.getMessage(Constants.ER_INVALID_DATE_RANGE, null, Locale.ENGLISH));
			}
			
			if (warningMessages.size()>0) {
				msg.setHasWarning(true);
				msg.setWarnings(warningMessages);
			}
			if (errorMessages.size()>0) {
				msg.setHasError(true);
				msg.setErrors(errorMessages);
			}
		} catch (Exception ex) {
			
			msg.setHasError(true);
			errorMessages.add(messageSource.getMessage(Constants.ER_TECH_PROB, null, Locale.ENGLISH));
			msg.setErrors(errorMessages);
			
			logger.error("Exception class: '{}' Method: '{}' message: '{}'", this.getClass().getName(), "validateForm",
					ex.getMessage());
		}

		return msg;
	}
}
