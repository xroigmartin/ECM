package xroigmartin.ecm.exceptions.api.domain.value;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DomainValueNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -8731333739948729156L;

	public DomainValueNotFoundException(Long id) {
		super("No find value with id: " + id);
	}
	
	public DomainValueNotFoundException(String value, Long domainId) {
		super("Not find value: " + value + " for domain id: " + domainId);
	}
}
