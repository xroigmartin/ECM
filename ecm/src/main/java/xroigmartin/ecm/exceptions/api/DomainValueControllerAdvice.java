package xroigmartin.ecm.exceptions.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import xroigmartin.ecm.exceptions.api.domain.value.DomainValueExistsApiException;
import xroigmartin.ecm.exceptions.api.domain.value.DomainValueNotFoundException;

@RestControllerAdvice
public class DomainValueControllerAdvice extends GlobalControllerAdvice{
	
	@ExceptionHandler(DomainValueNotFoundException.class)
	public ResponseEntity<ApiError> handlerDomainNotFound(DomainValueNotFoundException ex){
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(DomainValueExistsApiException.class)
	public ResponseEntity<ApiError> handlerDomainNotFound(DomainValueExistsApiException ex){
		ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
	}
}
