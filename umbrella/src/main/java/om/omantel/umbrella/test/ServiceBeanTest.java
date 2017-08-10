/**
 * 
 */
package om.omantel.umbrella.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import om.omantel.umbrella.config.UmbConfig;
import om.omantel.umbrella.service.LoginService;


/**
 * @author 70003
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=UmbConfig.class)
public class ServiceBeanTest {
	
	@Autowired
	private LoginService cd;
	
	@Test
	public void cdShouldNotBeNull() {
		assertNotNull(cd);
	}
}
