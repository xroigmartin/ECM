package xroigmartin.ecm.exceptions.domain;

public class CodeDomainExistsException extends RuntimeException {

	private static final long serialVersionUID = -1371488323815099165L;
	
	public CodeDomainExistsException(String message) {
		super("A domain with that code already exists");
	}

}
