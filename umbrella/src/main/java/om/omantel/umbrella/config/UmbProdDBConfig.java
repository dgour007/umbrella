/**
 * 
 */
package om.omantel.umbrella.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import om.omantel.umbrella.annotation.ProdProfile;

/**
 * @author Dhiraj Gour
 * @since 26 July 2017
 * @version 1.0
 */

@Configuration
@ProdProfile
public class UmbProdDBConfig {

	@Bean(name="posds")
	public JndiObjectFactoryBean PosDataSource () {
		JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
		jndiObjectFB.setJndiName("jdbc/POS");
		jndiObjectFB.setResourceRef(true);
		jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
		return jndiObjectFB;
	}
}
