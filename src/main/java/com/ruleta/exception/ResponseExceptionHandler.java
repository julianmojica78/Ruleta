package com.ruleta.exception;

import java.io.FileNotFoundException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	

	@ExceptionHandler(NotFoundModelException.class)
	public final ResponseEntity<ErrorWrapper> manejadorModelException(NotFoundModelException ex,WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(),request.getDescription(false));
		   return new ResponseEntity<ErrorWrapper>(er, HttpStatus.NOT_FOUND);					
	}
	
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<ErrorWrapper> manejarArgumentRequiredException(ArgumentRequiredException ex,
			WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(),request.getDescription(false));
		   return new ResponseEntity<ErrorWrapper>(er, HttpStatus.BAD_REQUEST);					
	}
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorWrapper> manejadorNullPointerException(NullPointerException ex,
			WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(),request.getDescription(false));
		 return new ResponseEntity<ErrorWrapper>(er, HttpStatus.INTERNAL_SERVER_ERROR);					
	}	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorWrapper> manejadorException(Exception ex,
			WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(),request.getDescription(false));
		 return new ResponseEntity<ErrorWrapper>(er, HttpStatus.INTERNAL_SERVER_ERROR);					
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(status.value(),status.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(status.value(),status.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(status.value(),status.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);		
	}	
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
				HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(status.value(),status.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.NOT_FOUND);
		}	
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorWrapper er = new ErrorWrapper(status.value(),status.getReasonPhrase(),ex.getMessage(),ex.getStackTrace(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}	
}

