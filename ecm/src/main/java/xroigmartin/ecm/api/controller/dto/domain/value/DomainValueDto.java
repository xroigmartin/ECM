package xroigmartin.ecm.api.controller.dto.domain.value;

import java.io.Serializable;

public class DomainValueDto implements Serializable{

	private static final long serialVersionUID = -3027606099126798616L;

	private Long domainValueId;
	private String value;
	private Boolean enable;
	private Long domainId;
	private String codeDomain;
	
	public Long getDomainValueId() {
		return domainValueId;
	}
	public void setDomainValueId(Long domainValueId) {
		this.domainValueId = domainValueId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Long getDomainId() {
		return domainId;
	}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	public String getCodeDomain() {
		return codeDomain;
	}
	public void setCodeDomain(String codeDomain) {
		this.codeDomain = codeDomain;
	}
}
