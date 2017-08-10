/**
 * 
 */
package om.omantel.umbrella.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

/**
 * @author 70003
 *
 */
public class LdapUser implements LdapUserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private LdapUserDetails details;
	
	private String fullName = null;
	private String eMailAddress = null;
	private String initials = null;
	private String department = null;
	private String mobile = null;
	
	public LdapUser(LdapUserDetails details, String fullName, String eMailAddress, String initials,
			String department, String mobile) {
	    this.details = details;
	    this.fullName = fullName;
	    this.eMailAddress = eMailAddress;
	    this.initials = initials;
	    this.department = department;
	    this.mobile = mobile;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return details.getAuthorities();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return details.getPassword();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return details.getUsername();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return details.isAccountNonExpired();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return details.isAccountNonLocked();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return details.isCredentialsNonExpired();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return details.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.CredentialsContainer#eraseCredentials()
	 */
	@Override
	public void eraseCredentials() {
		details.eraseCredentials();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.ldap.userdetails.LdapUserDetails#getDn()
	 */
	@Override
	public String getDn() {
		return details.getDn();
	}
	
	public String getFullName() {
		return fullName;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public String getInitials() {
		return initials;
	}

	public String getDepartment() {
		return department;
	}

	public String getMobile() {
		return mobile;
	}
}
