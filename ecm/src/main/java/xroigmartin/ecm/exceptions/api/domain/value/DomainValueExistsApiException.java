package xroigmartin.ecm.exceptions.api.domain.value;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import xroigmartin.ecm.exceptions.domain.DomainValueExistsException;

@ResponseStatus(HttpStatus.CONFLICT)
public class DomainValueExistsApiException extends DomainValueExistsException{
	
	private static final long serialVersionUID = 975157909103601344L;

	public DomainValueExistsApiException(String value, Long domainId) {
		super(value, domainId);
	}
}
