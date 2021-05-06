package xroigmartin.ecm.api.controller.dto.domain.value;

import java.io.Serializable;

public class AddDomainValueDto implements Serializable{

	private static final long serialVersionUID = -7100614452462909431L;

	private String value;
	private Long domainId;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getDomainId() {
		return domainId;
	}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
