package xroigmartin.ecm.model.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "domain")
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "domain_id")
	private Long id;
	
	@Column(name = "code_domain")
	private String codeDomain;
	
	@Column(name="code_domain_text")
	private String codeDomainText;
	
	@Column(name = "is_enable")
	private boolean enable = true;
	
	@OneToMany(mappedBy = "domain")
	private List<DomainValue> valuesOfDomain;

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

	public boolean isEnable() {
		return enable;
	}
	
	public void changeEnable() {
		this.enable = !this.enable;
	}
	
	public List<DomainValue> getValuesOfDomain() {
		return valuesOfDomain;
	}

	public void setValuesOfDomain(List<DomainValue> valuesOfDomain) {
		this.valuesOfDomain = valuesOfDomain;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codeDomain, id);
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
		return Objects.equals(codeDomain, other.codeDomain) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Domain [domainId=" + id + ", codeDomain=" + codeDomain + ", codeDomainText=" + codeDomainText
				+ ", enable=" + enable + "]";
	}
	
}
