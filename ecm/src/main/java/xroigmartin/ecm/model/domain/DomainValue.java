package xroigmartin.ecm.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "domain_value")
public class DomainValue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "domain_value_id")
	private Long id;
	
	@Column(name="value")
	@NotNull
	private String value;
	
	@Column(name="is_enable")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
}
