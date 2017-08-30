/**
 * 
 */
package om.omantel.umbrella.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import om.omantel.umbrella.annotation.ProdProfile;
import om.omantel.umbrella.annotation.TestProfile;
import om.omantel.umbrella.bean.AgencyInventoryData;
import om.omantel.umbrella.bean.IdName;
import om.omantel.umbrella.dao.AgencyInventoryDao;
import om.omantel.umbrella.util.Queries;
import oracle.jdbc.driver.OracleTypes;

/**
 * @author Dhiraj Gour
 * @date 16 August 2017
 *
 */
@Repository
@ProdProfile
@TestProfile
public class AgencyInventoryDaoImpl implements AgencyInventoryDao {
	
	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public AgencyInventoryDaoImpl (
			@Qualifier("posds") DataSource dataSource) {
		this.dataSource=dataSource;
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public List<AgencyInventoryData> getAgencyInvSummaryData(List<String> itemCodes, List<String> counterIds, List<Integer> agencyIds, String startDate, String endDate)  throws SQLException {
				
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("item_codes", itemCodes)
				.addValue("counterids", counterIds)
				.addValue("agencyids", agencyIds)
				.addValue("startdate",startDate)
				.addValue("enddate",endDate);
		
		return namedParameterJdbcTemplate.query(
				Queries.GET_AGENCY_INV_SUMMARY, in, this::mapSummaryItems);
	}

	@Override
	public AgencyInventoryData getAgencyInvDetailsData(String counterId, String startDate, String endDate)  throws SQLException{
		AgencyInventoryData data = new AgencyInventoryData();
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IdName> getCounterList(String ipAddress, int authority) throws SQLException {
		
		List<IdName> counterList = new ArrayList<IdName>(0);
		
		SimpleJdbcCall procedureCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("get_counter_list")
				.declareParameters(
						new SqlParameter("in_ip_address",Types.VARCHAR),
						new SqlParameter("in_authority",Types.INTEGER),
		                new SqlOutParameter("out_error_code", Types.INTEGER),
		                new SqlOutParameter("out_error_message", Types.VARCHAR),
		                new SqlOutParameter("out_counter_list", OracleTypes.CURSOR, this::mapCounterList));
				
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("in_ip_address", ipAddress)
				.addValue("in_authority", authority);
		
		Map<String,Object> out = procedureCall.execute(in);
		
		int status = (Integer)out.get("out_error_code");
		
		if (status==0) {
			counterList = (List<IdName>)out.get("out_counter_list");
		}
		
		return counterList;
	}

	@Override
	public List<String> getAgencyItemList()  throws SQLException{
		return namedParameterJdbcTemplate.query(
				Queries.GET_AGENCY_ITEMS, this::mapAgencyItems);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IdName> getAgencyList(String ipAddress, int authority)  throws SQLException{
		
		List<IdName> agencyList = new ArrayList<IdName>(0);
		
		SimpleJdbcCall procedureCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("get_agency_list")
				.declareParameters(
						new SqlParameter("in_ip_address",Types.VARCHAR),
						new SqlParameter("in_authority",Types.INTEGER),
		                new SqlOutParameter("out_error_code", Types.INTEGER),
		                new SqlOutParameter("out_error_message", Types.VARCHAR),
		                new SqlOutParameter("out_agency_list", OracleTypes.CURSOR, this::mapAgencyList));
				
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("in_ip_address", ipAddress)
				.addValue("in_authority", authority);
		
		Map<String,Object> out = procedureCall.execute(in);
		
		int status = (Integer)out.get("out_error_code");
		
		if (status==0) {
			agencyList = (List<IdName>)out.get("out_agency_list");
		}
		
		return agencyList;
	}
	
	private IdName mapCounterList (ResultSet rs, int row) throws SQLException {
		IdName idName = new IdName();
		idName.setName(rs.getString("counter_id"));
		idName.setValue(rs.getString("counter_name"));
		return idName;
	}
	
	private String mapAgencyItems (ResultSet rs, int row) throws SQLException {
		return rs.getString("item_code");
	}
	
	private IdName mapAgencyList (ResultSet rs, int row) throws SQLException {
		IdName idName = new IdName();
		idName.setId(rs.getInt("agency_id"));
		idName.setName(rs.getString("agency_desc"));
		return idName;
	}
	
	private AgencyInventoryData mapSummaryItems (ResultSet rs, int row) throws SQLException {
		AgencyInventoryData data = new AgencyInventoryData();
		data.setItemCode(rs.getString("item_code"));
		data.setItemId(rs.getString("item_id"));
		data.setCounterId(rs.getString("counter_id"));
		data.setAgencyDesc(rs.getString("agency"));
		data.setTransType(rs.getString("transaction_type"));
		data.setTransDesc(rs.getString("transaction_desc"));
		data.setQuantity(rs.getInt("total_qty"));
		return data;
	}
}
