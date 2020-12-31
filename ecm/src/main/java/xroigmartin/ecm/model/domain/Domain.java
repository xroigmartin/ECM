package xroigmartin.ecm.model.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain")
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "domain_id")
	private Long domainId;
	
	@Column(name = "code_domain")
	private String codeDomain;
	
	@Column(name="code_domain_text")
	private String codeDomainText;
	
	@Column(name = "is_enable")
	private boolean enable = true;

	public Domain() {
		super();
	}

	public Domain(String codeDomain) {
		super();
		this.codeDomain = codeDomain;
	}

	public Domain(String codeDomain, String codeDomainText) {
		super();
		this.codeDomain = codeDomain;
		this.codeDomainText = codeDomainText;
	}

	public Domain(String codeDomain, String codeDomainText, boolean enable) {
		super();
		this.codeDomain = codeDomain;
		this.codeDomainText = codeDomainText;
		this.enable = enable;
	}

	public Long getDomainId() {
		return domainId;
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

	public boolean isEnable() {
		return enable;
	}
	
	public void changeEnable() {
		this.enable = !this.enable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeDomain, domainId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Domain other = (Domain) obj;
		return Objects.equals(codeDomain, other.codeDomain) && Objects.equals(domainId, other.domainId);
	}

	@Override
	public String toString() {
		return "Domain [domainId=" + domainId + ", codeDomain=" + codeDomain + ", codeDomainText=" + codeDomainText
				+ ", enable=" + enable + "]";
	}
	
}
