package br.com.digisystem.exceptions;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodValidation(
			MethodArgumentNotValidException e,
			HttpServletRequest request) {

		ValidationError error = new ValidationError();
		
		error.setError("Validation error");
		error.setTimestamp(System.currentTimeMillis());
		error.setMessage("Error in data validation of the operation");
		error.setPath(request.getRequestURI());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		
		for (int i=0; i < e.getBindingResult().getFieldErrors().size(); i++) {
			String fieldName = e.getBindingResult().getFieldErrors().get(i).getField();
			String message = e.getBindingResult().getFieldErrors().get(i).getDefaultMessage();
			error.addError(fieldName, message);
		}
		
		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<StandardError> noSuchElement(
			NoSuchElementException e,
			HttpServletRequest request){
		
		StandardError error = new StandardError();
		
		error.setError("No such element error");
		error.setTimestamp(System.currentTimeMillis());
		error.setMessage("The element was not found in the database");
		error.setPath(request.getRequestURI());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		
		return ResponseEntity.internalServerError().body(error);
	}
	
	@ExceptionHandler(ObjNotFoundException.class)
	public ResponseEntity<StandardError> objNotFound(
			ObjNotFoundException e,
			HttpServletRequest request){
		
		StandardError error = new StandardError();
		
		error.setError("Object not found error");
		error.setTimestamp(System.currentTimeMillis());
		error.setMessage(e.getMessage());
		error.setPath(request.getRequestURI());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(error);
	}
}
