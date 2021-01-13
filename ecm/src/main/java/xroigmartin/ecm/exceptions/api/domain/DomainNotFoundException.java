package xroigmartin.ecm.exceptions.api.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DomainNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -8731333739948729156L;

	public DomainNotFoundException(Long id) {
		super("No se puede encontrar el dominio con la ID: " + id);
	}
}
