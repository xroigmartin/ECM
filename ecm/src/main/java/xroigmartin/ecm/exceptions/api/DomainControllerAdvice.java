package xroigmartin.ecm.exceptions.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import xroigmartin.ecm.exceptions.api.domain.DomainNotFoundException;
import xroigmartin.ecm.exceptions.domain.CodeDomainExistsException;

@RestControllerAdvice
public class DomainControllerAdvice {
	
	@ExceptionHandler(DomainNotFoundException.class)
	public ResponseEntity<ApiError> handlerDomainNotFound(DomainNotFoundException ex){
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(CodeDomainExistsException.class)
	public ResponseEntity<ApiError> handlerCodeDomainExists(CodeDomainExistsException ex){
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
	}
}
