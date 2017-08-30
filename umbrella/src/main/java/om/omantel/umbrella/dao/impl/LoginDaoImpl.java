/**
 * 
 */
package om.omantel.umbrella.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import om.omantel.umbrella.annotation.ProdProfile;
import om.omantel.umbrella.annotation.TestProfile;
import om.omantel.umbrella.bean.IdName;
import om.omantel.umbrella.bean.Menu;
import om.omantel.umbrella.bean.User;
import om.omantel.umbrella.dao.LoginDao;
import om.omantel.umbrella.util.Queries;
import oracle.jdbc.driver.OracleTypes;

/**
 * @author Dhiraj Gour
 * @since 26 July 2017
 * @version 1.0
 */

@Repository
@ProdProfile
@TestProfile
public class LoginDaoImpl implements LoginDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LoginDaoImpl (@Qualifier("jdbcpos") JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserDetails(String userId, String ipAddress) {
		
		User user = new User();
		
		SimpleJdbcCall procedureCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("get_user_details")
				.declareParameters(
						new SqlParameter("in_user_id",Types.VARCHAR),
						new SqlParameter("in_ip_address",Types.VARCHAR),
		                new SqlOutParameter("out_theme", Types.VARCHAR),
		                new SqlOutParameter("out_role", Types.INTEGER),
		                new SqlOutParameter("out_role_desc", Types.VARCHAR),
		                new SqlOutParameter("out_app", Types.INTEGER),
		                new SqlOutParameter("out_authority", Types.INTEGER),
		                new SqlOutParameter("out_error_code", Types.INTEGER),
		                new SqlOutParameter("out_error_message", Types.VARCHAR),
		                new SqlOutParameter("out_app_list", OracleTypes.CURSOR, this::mapAppList));
				
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("in_user_id", userId)
				.addValue("in_ip_address", ipAddress);
		
		Map<String,Object> out = procedureCall.execute(in);
		
		int status = (Integer)out.get("out_error_code");
		
		if (status==0) {
			user.setTheme((String)out.get("out_theme"));
			user.setRoleId((Integer)out.get("out_role"));
			user.setAppId((Integer)out.get("out_app"));
			user.setRoleDesc((String)out.get("out_role_desc"));
			user.setAuthority((Integer)out.get("out_authority"));
			user.setAppList((List<IdName>)out.get("out_app_list"));
		}
		
		return user;
	}
	
	@Override
	public List<Menu> getMenu(int roleId, int appId) {
		return jdbcTemplate.query(
				Queries.GET_MENU, this::mapMenu, roleId, appId);
	}

	@Override
	public int updateTheme(String userId, String theme) {
		int cnt=jdbcTemplate.update(Queries.UPDATE_THEME, theme, userId);
		return cnt;
	}

	private Menu mapMenu (ResultSet rs, int row) throws SQLException {
		Menu menu = new Menu();
		menu.setMenuId(rs.getInt("menu_id"));
		menu.setMenuName(rs.getString("menu_name"));
		menu.setParentMenuId(rs.getInt("parent_menu"));
		menu.setAction(rs.getString("action"));
		menu.setIconClass(rs.getString("icon_class"));
		return menu;
	}
	
	private IdName mapAppList (ResultSet rs, int row) throws SQLException {
		IdName idName = new IdName();
		idName.setId(rs.getInt("app_id"));
		idName.setName(rs.getString("app"));
		idName.setValue(rs.getString("icon_class"));
		return idName;
	}
}
