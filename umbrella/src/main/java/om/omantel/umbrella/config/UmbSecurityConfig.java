/**
 * 
 */
package om.omantel.umbrella.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import om.omantel.umbrella.annotation.ProdProfile;
import om.omantel.umbrella.annotation.TestProfile;
import om.omantel.umbrella.security.LdapUserDetailsContextMapper;

/**
 * @author Dhiraj Gour
 * @since 27 July 2017
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@ProdProfile
@TestProfile
public class UmbSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()										//authorization
			.antMatchers("/logon").permitAll()
			.antMatchers("/login").anonymous()
			.antMatchers("/log_out").anonymous()
			.antMatchers("/invalid").anonymous()
			.antMatchers("/session").anonymous()
			.antMatchers("/static/**").permitAll()
			.anyRequest().authenticated()
			.and()
			//.requiresChannel().anyRequest().requiresSecure()			//https channel		
			//.and()
			.formLogin()												//login
			.loginPage("/logon")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/auth")
			.failureUrl("/invalid")
			.and()
			.logout()													//logout
			.invalidateHttpSession(true) 								//true by default
			.logoutUrl("/perform_logout")
			.logoutSuccessUrl("/log_out")
			.deleteCookies("JSESSIONID")
			.and()
			.exceptionHandling()										//Exception Handling
			.accessDeniedPage("/access_denied")
			.and()
			.sessionManagement()										//Session Management
			.sessionFixation().migrateSession()
			.invalidSessionUrl("/logon")
			.sessionAuthenticationErrorUrl("/session");
			//.and() 													//Uncomment this to enable remember me functionality
			//.rememberMe()												//Remember Me Functionality
			//.tokenValiditySeconds(2419200)
			//.key("spittrKey");
			
		http.sessionManagement().maximumSessions(1);
	}
	
	//LDAP Authentication
	//Start
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.ldapAuthentication()
			.contextSource(ldapContextSource())
			.ldapAuthoritiesPopulator(ldapAuthority())
			.userSearchFilter("(sAMAccountName={0})")
			.userDetailsContextMapper(userDetails());
	}
	
	@Bean
    public BaseLdapPathContextSource ldapContextSource() {
        LdapContextSource bean = new LdapContextSource();
        bean.setUrl("ldap://hqdc-01:389/");
        bean.setBase("DC=omanmobile,DC=co,DC=om");
        bean.setUserDn("CN=Dhiraj Gour,OU=Users,OU=IT&Network,OU=OTG,DC=omanmobile,DC=co,DC=om");
        bean.setPassword("guA@Aug2017");
        bean.setPooled(true);
        bean.setReferral("follow");
        
        return bean;
    }
	
	@Bean
	public DefaultLdapAuthoritiesPopulator ldapAuthority() {
		DefaultLdapAuthoritiesPopulator bean = 
				new DefaultLdapAuthoritiesPopulator(ldapContextSource(), "");
		bean.setSearchSubtree(true);
		bean.setIgnorePartialResultException(true);
		bean.setDefaultRole("ROLE_USER");
		return bean;
	}
	
	@Bean
	public UserDetailsContextMapper userDetails() {
		return new LdapUserDetailsContextMapper();
	}
	//End
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
	//Sample Code
	//JDBC Authentication
	/*@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(
					"select username, password, true " +
					"from Spitter where username=?")
			.authoritiesByUsernameQuery(
					"select username, 'ROLE_USER' from Spitter where username=?")
			.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
	}*/
}