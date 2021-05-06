package xroigmartin.ecm.exceptions.domain;

public class DomainValueExistsException extends RuntimeException {

	private static final long serialVersionUID = -1371488323815099165L;
	
	public DomainValueExistsException(String message) {
		super(message);
	}
	
	public DomainValueExistsException(String value, Long domainId) {
		super("Domain value with the value " + value + "already exists for the domain with the id " + domainId);
	}

}
