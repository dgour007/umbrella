/**
 * 
 */
package om.omantel.umbrella.bean;

/**
 * @author Dhiraj Gour
 * @date 16 August 2017 
 *
 */
public class AgencyInventoryData {
	
	private String itemCode = null;
	private String itemId = null;
	private String counterId = null;
	private String agencyDesc = null;
	private String transType = null;
	private String transDesc = null;
	private String transDate = null;
	private int quantity = 0;
	private String serialNo = null;
	private String batchNo = null;
	private String fixedLineNo = null;
	
	public String getFixedLineNo() {
		return fixedLineNo;
	}
	public void setFixedLineNo(String fixedLineNo) {
		this.fixedLineNo = fixedLineNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getCounterId() {
		return counterId;
	}
	public void setCounterId(String counterId) {
		this.counterId = counterId;
	}
	public String getAgencyDesc() {
		return agencyDesc;
	}
	public void setAgencyDesc(String agencyDesc) {
		this.agencyDesc = agencyDesc;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransDesc() {
		return transDesc;
	}
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
}
