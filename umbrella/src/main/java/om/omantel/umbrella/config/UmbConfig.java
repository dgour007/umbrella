/**
 * 
 */
package om.omantel.umbrella.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import om.omantel.umbrella.annotation.ProdProfile;
import om.omantel.umbrella.annotation.TestProfile;
import om.omantel.umbrella.aspect.AspectMarker;
import om.omantel.umbrella.dao.DaoMarker;
import om.omantel.umbrella.service.ServiceMarker;

/**
 * @author Dhiraj Gour
 * @since 24 July 2017
 * @version 1.0
 */

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses={ServiceMarker.class, DaoMarker.class, AspectMarker.class, UmbPropertiesConfig.class},
excludeFilters={@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)})
public class UmbConfig {
	
	@ProdProfile
	@TestProfile
	@Bean(name="jdbcpos")
	public JdbcTemplate jdbcTemplate(@Qualifier("posds") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}