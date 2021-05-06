package xroigmartin.ecm.api.controller.dto.domain.value;

import java.io.Serializable;

public class EditDomainValueDto implements Serializable{

	private static final long serialVersionUID = 8970262204020803952L;

	private String value;
	private boolean enable;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
