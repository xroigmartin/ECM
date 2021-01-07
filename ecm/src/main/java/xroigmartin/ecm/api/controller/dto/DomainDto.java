package xroigmartin.ecm.api.controller.dto;

public class DomainDto {

	private Long id;
	private String codeDomain;
	private String codeDomainText;
	private Boolean enable;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
