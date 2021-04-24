package xroigmartin.ecm.exceptions.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import xroigmartin.ecm.exceptions.api.domain.DomainNotFoundException;
import xroigmartin.ecm.exceptions.api.domain.value.DomainValueNotFoundException;
import xroigmartin.ecm.exceptions.domain.CodeDomainExistsException;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage());
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}
	
}
