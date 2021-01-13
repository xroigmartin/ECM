package xroigmartin.ecm.exceptions.api;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class ApiError {

	@NonNull
	private HttpStatus status;
	@JsonFormat(shape=Shape.STRING, pattern="dd/MM/yyyy hh:mm:ss")
	private LocalDateTime dateTime = LocalDateTime.now();
	@NonNull
	private String message;
	
	public ApiError() {
		super();
	}
	
	public ApiError(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
