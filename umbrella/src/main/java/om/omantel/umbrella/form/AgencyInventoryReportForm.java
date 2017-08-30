/**
 * 
 */
package om.omantel.umbrella.form;

import java.util.ArrayList;
import java.util.List;

import om.omantel.umbrella.bean.AgencyInventoryData;
import om.omantel.umbrella.bean.IdName;

/**
 * @author Dhiraj Gour
 * @date 16 August 2017
 *
 */
public class AgencyInventoryReportForm {
	
	private List<String> itemCodes = new ArrayList<String>(0);
	private List<String> counterIds = new ArrayList<String>(0);
	private List<Integer> agencyIds = new ArrayList<Integer>(0);
	private String startDate = null;
	private String endDate = null;
	private String errorCode = null;
	private String warningCode = null;
	
	private List<String> itemList = null;
	private List<IdName> counterList = null;
	private List<IdName> agencyList = null;
	
	private List<AgencyInventoryData> agencyInvSummary = null;
	private List<AgencyInventoryData> agencyInvDetails = null;
	
	public String getWarningCode() {
		return warningCode;
	}
	public void setWarningCode(String warningCode) {
		this.warningCode = warningCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public List<String> getItemCodes() {
		return itemCodes;
	}
	public void setItemCodes(String itemCodes) {
		if (itemCodes != null || !"".equalsIgnoreCase(itemCodes)) {
			String[] itCds = itemCodes.split(",");
			for (String itCd : itCds) {
				if (!"".equalsIgnoreCase(itCd)) {
					this.itemCodes.add(itCd);
				}
			}
		}
	}
	public List<String> getCounterIds() {
		return counterIds;
	}
	public void setCounterIds(String counterIds) {
		if (counterIds != null || !"".equalsIgnoreCase(counterIds)) {
			String[] cntids = counterIds.split(",");
			for (String cntid : cntids) {
				if (!"".equalsIgnoreCase(cntid)) {
					this.counterIds.add(cntid);
				}
			}
		}
	}
	public List<Integer> getAgencyIds() {
		return agencyIds;
	}
	public void setAgencyIds(String agencyIds) {
		if (agencyIds != null || !"".equalsIgnoreCase(agencyIds)) {
			String[] agIds = agencyIds.split(",");
			for (String agid : agIds) {
				if (!"".equalsIgnoreCase(agid)) {
					this.agencyIds.add(Integer.valueOf(agid));
				}
			}
		}
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public List<String> getItemList() {
		return itemList;
	}
	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}
	public List<IdName> getCounterList() {
		return counterList;
	}
	public void setCounterList(List<IdName> counterList) {
		this.counterList = counterList;
	}
	public List<IdName> getAgencyList() {
		return agencyList;
	}
	public void setAgencyList(List<IdName> agencyList) {
		this.agencyList = agencyList;
	}
	public List<AgencyInventoryData> getAgencyInvSummary() {
		return agencyInvSummary;
	}
	public void setAgencyInvSummary(List<AgencyInventoryData> agencyInvSummary) {
		this.agencyInvSummary = agencyInvSummary;
	}
	public List<AgencyInventoryData> getAgencyInvDetails() {
		return agencyInvDetails;
	}
	public void setAgencyInvDetails(List<AgencyInventoryData> agencyInvDetails) {
		this.agencyInvDetails = agencyInvDetails;
	}
}
