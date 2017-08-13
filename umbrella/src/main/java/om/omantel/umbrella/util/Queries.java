/**
 * 
 */
package om.omantel.umbrella.util;

/**
 * @author Dhiraj Gour
 * @date 12 August 2017
 *
 */
public class Queries {

	public final static String UPDATE_THEME = "update umb_users set theme=? where user_id=?";
	
	public final static String GET_MENU = "select um.menu_id, menu_name, parent_menu, action, "
			+ "icon_class from umb_menu um, umb_role_menus urm where um.menu_id = urm.menu_id and "
			+ "urm.role_id=? and um.app_id=? order by parent_menu, um.seq asc";
}
