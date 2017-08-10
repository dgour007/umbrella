/**
 * 
 */
package om.omantel.umbrella.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author Dhiraj Gour
 * @date 05 Aug 2017
 */
@Configuration
@PropertySource("classpath:res/application.properties")
public class UmbPropertiesConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer
    	propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
