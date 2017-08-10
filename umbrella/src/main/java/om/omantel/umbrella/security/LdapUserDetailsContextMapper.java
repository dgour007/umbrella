/**
 * 
 */
package om.omantel.umbrella.security;

import java.util.Collection;

import javax.naming.NamingException;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

/**
 * @author 70003
 *
 */
public class LdapUserDetailsContextMapper extends LdapUserDetailsMapper implements UserDetailsContextMapper {
	
	private LdapUser userDetails = null;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public LdapUserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection authorities) {
		
		LdapUserDetailsImpl details = null;
		String name = null;
		String emailAddress = null;
		LdapUser ldapUser = null;
		String department = null;
		String mobile = null;
		String initials = null;
		
		try {
			details = (LdapUserDetailsImpl) super.mapUserFromContext(ctx, username, authorities);
			name = (String)ctx.getAttributes().get("name").get(0);
			emailAddress = (String)ctx.getAttributes().get("mail").get(0);
			initials = (String)ctx.getAttributes().get("initials").get(0);
			department = (String)ctx.getAttributes().get("department").get(0);
			mobile = (String)ctx.getAttributes().get("mobile").get(0);
			
			ldapUser = new LdapUser(details, name, emailAddress, initials, department, mobile);
			this.userDetails = ldapUser;
			
		} catch (NamingException ne) {
			ne.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return ldapUser;
    }
	 
	 @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		 
    }
	 
	public LdapUser getUserDetails() {
		return this.userDetails;
	}
}