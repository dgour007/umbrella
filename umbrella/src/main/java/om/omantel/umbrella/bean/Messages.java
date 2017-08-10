/**
 * 
 */
package om.omantel.umbrella.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dhiraj Gour
 * @since 30 July 2017
 * @version 1.0
 */
public class Messages implements Serializable {
	
	static final long serialVersionUID = 103844514947365244L;
	
	public Messages () {
		this.reset();
	}
	
	private boolean hasSuccess = false;
	private boolean hasWarning = false;
	private boolean hasError = false;
	private boolean hasInfo = false;
	private boolean hasPrimary = false;

	private List<String> success = null;
	private List<String> warnings = null;
	private List<String> errors = null;
	private List<String> infos = null;
	private List<String> primaries = null;
	
	public void reset() {
		this.hasError=false;
		this.hasSuccess=false;
		this.hasWarning=false;
		this.hasInfo=false;
		this.hasPrimary=false;
		this.errors=null;
		this.warnings=null;
		this.success=null;
		this.infos = null;
		this.primaries = null;
	}
	
	public boolean isHasSuccess() {
		return hasSuccess;
	}
	
	public void setHasSuccess(boolean hasSuccess) {
		this.hasSuccess = hasSuccess;
	}
	
	public boolean isHasWarning() {
		return hasWarning;
	}
	
	public void setHasWarning(boolean hasWarning) {
		this.hasWarning = hasWarning;
	}
	
	public boolean isHasError() {
		return hasError;
	}
	
	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
	public List<String> getSuccess() {
		return success;
	}
	
	public void setSuccess(List<String> success) {
		this.success = success;
	}
	
	public List<String> getWarnings() {
		return warnings;
	}
	
	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public boolean isHasInfo() {
		return hasInfo;
	}

	public void setHasInfo(boolean hasInfo) {
		this.hasInfo = hasInfo;
	}

	public List<String> getInfos() {
		return infos;
	}

	public void setInfos(List<String> infos) {
		this.infos = infos;
	}

	public boolean isHasPrimary() {
		return hasPrimary;
	}

	public void setHasPrimary(boolean hasPrimary) {
		this.hasPrimary = hasPrimary;
	}

	public List<String> getPrimaries() {
		return primaries;
	}

	public void setPrimaries(List<String> primaries) {
		this.primaries = primaries;
	}
}