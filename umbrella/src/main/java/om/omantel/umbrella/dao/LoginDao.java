/**
 * 
 */
package om.omantel.umbrella.dao;

import java.util.List;

import om.omantel.umbrella.bean.Menu;
import om.omantel.umbrella.bean.User;

/**
 * @author Dhiraj Gour
 * @since 26 July 2017
 * @version 1.0
 */
public interface LoginDao {
	
	User getUserDetails (String userId, String ipAddress);
	
	List<Menu> getMenu (int roleId, int appId);
	
	int updateTheme (String userId, String theme);
}
