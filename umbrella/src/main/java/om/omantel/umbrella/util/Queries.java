/**
 * 
 */
package om.omantel.umbrella.util;

/**
 * @author Dhiraj Gour
 * @date 12 August 2017
 *
 */
public class Queries {

	public final static String UPDATE_THEME = "update umb_users set theme=? where user_id=?";
	
	public final static String GET_MENU = "select um.menu_id, menu_name, parent_menu, action, "
			+ "icon_class from umb_menu um, umb_role_menus urm where um.menu_id = urm.menu_id and "
			+ "urm.role_id=? and um.app_id=? order by parent_menu, um.seq asc";
	
	public final static String GET_AGENCY_ITEMS = "select service_code item_code from fixed_inv_items order by service_code";
	
	public final static String GET_AGENCY_INV_SUMMARY = "select * from ( select pcat.service_code item_code, pcat.erp_item_id item_id, pcat.counter_id, " + 
			"pcar.agency_desc agency, pcat.transaction_type, dtr.transaction_desc, sum(pcat.quantity) total_qty " + 
			"from pos_counter_agency_trans pcat, pos_counter_agency_ref pcar, dma_transaction_type_ref dtr where pcat.agency_id=pcar.agency_id " + 
			"and trim(pcat.transaction_type)=trim(dtr.transaction_type) and pcat.transaction_type in ('F','FIR','IS','FRC') " + 
			"and pcat.service_code in (:item_codes) and  pcat.counter_id in (:counterids) and pcat.agency_id in (:agencyids) " + 
			"and trunc(pcat.SYS_CREATION_DATE) between to_date(:startdate,'yyyy-mm-dd') and to_date(:enddate,'yyyy-mm-dd') " + 
			"group by pcat.service_code, pcat.erp_item_id, pcat.counter_id, " + 
			"pcar.agency_desc,  pcat.transaction_type, dtr.transaction_desc " + 
			"union " + 
			"select ioh.service_code item_code, ioh.erp_item_id item_id, ioh.counter_id, pcar.agency_desc agency, " + 
			"'O' transaction_type, 'Items on hand' transaction_desc, sum(ioh.AVAILABLE_QTY) total_qty " + 
			"from FIXED_AGENCY_ONHAND_V ioh, pos_counter_agency_ref pcar where ioh.v_agency_id=pcar.agency_id " + 
			"and ioh.service_code in (:item_codes) and ioh.v_agency_id in (:agencyids) " + 
			"group by ioh.service_code, ioh.erp_item_id, ioh.counter_id, pcar.agency_desc) " + 
			"order by item_code, counter_id, agency, transaction_type ";
	
	/*select PCAT.SERVICE_CODE ITEM_CODE, PCAT.ERP_ITEM_ID ITEM_ID, PCAT.COUNTER_ID, pcar.agency_desc agency, PCAT.TRANSACTION_TYPE, DTR.TRANSACTION_DESC, 
	pcat.SYS_CREATION_DATE TRANSACTION_DATE, decode(PCAS.SERIAL_NUMBER,null,PCAT.QUANTITY,1) QUANTITY , PCAS.SERIAL_NUMBER, PCAT.TRANSACTION_ID batch_no 
	from pos_counter_agency_trans pcat, pos_counter_agency_serials pcas, pos_counter_agency_ref pcar, dma_transaction_type_ref dtr 
	where pcat.agency_id=pcar.agency_id and PCAT.TRANSACTION_ID=PCAS.TRANSACTION_ID(+)
	and trim(PCAT.TRANSACTION_TYPE)=trim(DTR.TRANSACTION_TYPE)
	and PCAT.TRANSACTION_TYPE in ('F','FIR','IS','FRC')
	order by PCAT.SERVICE_CODE, PCAT.COUNTER_ID, pcar.agency_desc, PCAT.TRANSACTION_ID, pcat.SYS_CREATION_DATE, PCAS.SERIAL_NUMBER*/
}