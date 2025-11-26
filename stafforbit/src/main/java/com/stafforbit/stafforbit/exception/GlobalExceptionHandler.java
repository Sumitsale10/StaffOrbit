package com.stafforbit.stafforbit.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
	        Map<String, String> err = Map.of("message", ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(e ->
	                errors.put(e.getField(), e.getDefaultMessage()));
	        return ResponseEntity.badRequest().body(errors);
	    }

	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<?> handleIllegalArg(IllegalArgumentException ex) {
	        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<?> handleOther(Exception ex) {

	        // Allow Spring to handle static resources (don't block images, css, js, favicon)
	        if (ex instanceof org.springframework.web.servlet.resource.NoResourceFoundException ||
	            ex instanceof org.springframework.web.servlet.NoHandlerFoundException) {
	            // rethrow so the DispatcherServlet can serve static resources
	            throw new RuntimeException(ex);
	        }

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(Map.of("message", "Something went wrong"));
	    }

}
