package xroigmartin.ecm.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class DomainValue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long domainValueId;
	
	@NotNull
	private String value;
	
	@NotNull
	private Boolean enable = true;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "domain_id")
	private Domain domain;

	public DomainValue() {
		super();
	}
	
	public DomainValue(@NotNull String value, @NotNull Domain domain) {
		super();
		this.value = value;
		this.domain = domain;
	}

	public DomainValue(@NotNull String value, @NotNull Boolean enable, @NotNull Domain domain) {
		super();
		this.value = value;
		this.enable = enable;
		this.domain = domain;
	}
	
	public Long getDomainValueId() {
		return domainValueId;
	}

	public String getValue() {
		return value;
	}

	public void changeValue(String value) {
		this.value = value;
	}

	public Boolean isEnable() {
		return enable;
	}

	public void changeEnable(Boolean enable) {
		this.enable = enable;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
}
