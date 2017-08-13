/**
 * 
 */
package om.omantel.umbrella.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Dhiraj Gour
 * @since 24 July 2017
 * @version 1.0
 */

@Configuration
@Import({UmbConfig.class, UmbTestDBConfig.class, 
	UmbProdDBConfig.class, UmbSecurityConfig.class})
@ImportResource("classpath:configurations/umbrella-config.xml")
public class UmbRootConfig {

}
