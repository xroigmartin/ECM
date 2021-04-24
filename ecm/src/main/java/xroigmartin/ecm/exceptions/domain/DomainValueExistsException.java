package xroigmartin.ecm.exceptions.domain;

public class DomainValueExistsException extends RuntimeException {

	private static final long serialVersionUID = -1371488323815099165L;
	
	public DomainValueExistsException(String message) {
		super(message);
	}

}
