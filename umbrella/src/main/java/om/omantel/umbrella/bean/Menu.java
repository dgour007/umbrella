/**
 * 
 */
package om.omantel.umbrella.bean;

import java.util.List;

/**
 * @author Dhiraj Gour
 * @date 11 August 2017
 *
 */
public class Menu {
	
	private int menuId = 0;
	private String menuName = null;
	private String action = null;
	private int parentMenuId = 0;
	private String iconClass = null;
	
	List<Menu> childMenu = null;
	
	public List<Menu> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
}
