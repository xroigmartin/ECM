package xroigmartin.ecm.model.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "domain_value")
public class DomainValue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="domain_value_id")
	private Long domainValueId;
	
	@NotNull
	@Column(name="value")
	private String value;
	
	@NotNull
	@Column(name="is_enable")
	private Boolean enable = true;

	@JsonBackReference
	@NotNull
	@ManyToOne
	@JoinColumn(name = "domain_id")
	private Domain domain;

	public DomainValue() {
		super();
	}
	
	public DomainValue(String value, Domain domain) {
		super();
		this.value = value;
		this.domain = domain;
	}
	
	public DomainValue(String value, Boolean enable, Domain domain) {
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

	public void changeEnable() {
		this.enable = !this.enable;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	@Override
	public int hashCode() {
		return Objects.hash(domain, domainValueId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainValue other = (DomainValue) obj;
		return Objects.equals(domain, other.domain) && Objects.equals(domainValueId, other.domainValueId)
				&& Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "DomainValue [domainValueId=" + domainValueId + ", value=" + value + ", enable=" + enable + ", domain="
				+ domain.toString() + "]";
	}
	
	
}
