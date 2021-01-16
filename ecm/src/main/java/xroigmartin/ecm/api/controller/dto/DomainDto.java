package xroigmartin.ecm.api.controller.dto;

public class DomainDto {

	private Long domainId;
	private String codeDomain;
	private String codeDomainText;
	private Boolean enable;
	
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
	public String getCodeDomainText() {
		return codeDomainText;
	}
	public void setCodeDomainText(String codeDomainText) {
		this.codeDomainText = codeDomainText;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	
}
