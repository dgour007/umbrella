/**
 * 
 */
package om.omantel.umbrella.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import om.omantel.umbrella.annotation.TestProfile;

/**
 * @author Dhiraj Gour
 * @since 26 July 2017
 * @version 1.0
 */
@Configuration
@TestProfile
public class UmbTestDBConfig {
	
	@Bean(name="posds")
	public DataSource PosDataSource () throws SQLException {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@(description=(address=(host=10.64.100.225)(protocol=tcp)(port=1521))(connect_data=(sid=CMATST)))");
		ds.setUsername("CMATEST1");
		ds.setPassword("CMATEST1");
		return ds;
	}
}
