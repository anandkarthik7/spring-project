package com.Project_amaze.exception;

public class AppointmentUnavailableException extends Exception{
	 
	private static final long serialVersionUID = 1L;
	
	 private String message;

	public AppointmentUnavailableException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	} 
	 
	 

}
