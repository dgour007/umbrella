/**
 * 
 */
package om.omantel.umbrella.bean;

/**
 * @author Dhiraj Gour
 * @date 11 August 2017
 *
 */
public class IdName {
	
	private int id = 0;
	private String name = null;
	private String value = null;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
