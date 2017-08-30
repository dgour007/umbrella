/**
 * 
 */
package om.omantel.umbrella.security;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dhiraj Gour
 * @date 30 August 2017
 *
 */
public class UserService {
 
    public Map<String, Object> getUserByUsername(String username) {
        Map<String, Object> userMap = null;
        
        userMap = new HashMap<>();
        userMap.put("username", username);
        userMap.put("password", "guA");
        userMap.put("role", "ROLE_USER");
        
        return userMap;
    }
}