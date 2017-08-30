/**
 * 
 */
package om.omantel.umbrella.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * @author Dhiraj Gour
 * @date 17 August 2017
 *
 */
public class Commons {

	public static List<String> getErrorMessage(MessageSource messageSource, String messageCode, Locale locale) {
		List<String> errorMessages = new ArrayList<String>();
		errorMessages.add(messageSource.getMessage(messageCode, null, locale));
		return errorMessages;
	}
	
	public static List<String> getSuccessMessage(MessageSource messageSource, String messageCode, Locale locale) {
		List<String> successMessages = new ArrayList<String>();
		successMessages.add(messageSource.getMessage(messageCode, null, locale));
		return successMessages;
	}
	
	public static List<String> getWarningMessage(MessageSource messageSource, String messageCode, Locale locale) {
		List<String> warningMessages = new ArrayList<String>();
		warningMessages.add(messageSource.getMessage(messageCode, null, locale));
		return warningMessages;
	}
	
	public static boolean isValidDate (String date, String format) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(format);
	    try {
	        df.parse(date);
	        return true;
	    } catch (ParseException e) {
	        return false;
	    }
	}
	
	public static boolean checkDateRange (String startdate, String endDate, String format) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
	        Date startD = df.parse(startdate);
	        Date endD = df.parse(endDate);
	        if (startD.before(endD)) {
	        	return true;
	        } else {
	        	return false;
	        }
	    } catch (ParseException e) {
	        return false;
	    }
	}
}