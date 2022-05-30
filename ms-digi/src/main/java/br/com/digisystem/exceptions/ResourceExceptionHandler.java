package br.com.digisystem.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodValidation(
			MethodArgumentNotValidException e,
			HttpServletRequest request) {
		StandardError error = new StandardError();
		error.setError("Validation error");
		error.setTimestamp(System.currentTimeMillis());
		error.setMessage("Error in data validation of the operation");
		error.setPath(request.getRequestURI());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.badRequest().body(error);
	}
}
