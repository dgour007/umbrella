/**
 * 
 */
package om.omantel.umbrella.dao;

import java.sql.SQLException;
import java.util.List;

import om.omantel.umbrella.bean.AgencyInventoryData;
import om.omantel.umbrella.bean.IdName;

/**
 * @author Dhiraj Gour
 * @date 16 August 2017
 *
 */
public interface AgencyInventoryDao {
	
	List<IdName> getCounterList(String ipAddress, int authority) throws SQLException;
	
	List<String> getAgencyItemList() throws SQLException;
	
	List<IdName> getAgencyList(String ipAddress, int authority) throws SQLException;
	
	List<AgencyInventoryData> getAgencyInvSummaryData(List<String> itemCodes, 
			List<String> counterIds, List<Integer> agencyIds, String startDate, String endDate)  
					throws SQLException;
	
	AgencyInventoryData getAgencyInvDetailsData(String counterId, String startDate, String endDate) 
			throws SQLException;
	
}
