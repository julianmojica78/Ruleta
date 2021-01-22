package com.ruleta.exception;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ErrorWrapper {
	
	private LocalTime timestamp;
	
	private int status;
	
	private String error;
	
	private String message;
	
	private StackTraceElement[] trace;
	
	private String path;
	
	
	

	public ErrorWrapper(int status, String error, String message, StackTraceElement[] trace,
			String path) {
		super();
		this.timestamp = LocalTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.trace = trace;
		this.path = path;
	}


	public LocalTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalTime timestamp) {
		this.timestamp = timestamp;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public StackTraceElement[] getTrace() {
		return trace;
	}


	public void setTrace(StackTraceElement[] trace) {
		this.trace = trace;
	}
	
	

}
