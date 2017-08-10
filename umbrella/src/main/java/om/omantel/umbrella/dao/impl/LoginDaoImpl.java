/**
 * 
 */
package om.omantel.umbrella.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import om.omantel.umbrella.annotation.ProdProfile;
import om.omantel.umbrella.annotation.TestProfile;
import om.omantel.umbrella.dao.LoginDao;

/**
 * @author Dhiraj Gour
 * @since 26 July 2017
 * @version 1.0
 */

@Repository
@ProdProfile
@TestProfile
public class LoginDaoImpl implements LoginDao {

	private JdbcOperations jdbcOperations;
	
	@Autowired
	public LoginDaoImpl (@Qualifier("jdbcpos") JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}
	
	public void testInsert() {
		jdbcOperations.update("insert into TEST values (sysdate, ?,?)",2,3);
	}
}
