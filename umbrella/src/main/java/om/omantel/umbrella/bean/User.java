/**
 * 
 */
package om.omantel.umbrella.bean;

import java.util.List;

/**
 * @author Dhiraj Gour
 * @date 04 August 2017
 *
 */
public class User {
	
	private int roleId = 0;
	private int appId = 0;
	
	private String userId = null;
	private String fullName = null;
	private String eMailAddress = null;
	private String initials = null;
	private String department = null;
	private String mobile = null;
	private String theme = null;
	private String roleDesc = null;
	
	List<IdName> appList = null;
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public List<IdName> getAppList() {
		return appList;
	}
	public void setAppList(List<IdName> appList) {
		this.appList = appList;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String geteMailAddress() {
		return eMailAddress;
	}
	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
