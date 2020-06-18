package com.cts.fse.projectmanager.utils;


import org.springframework.validation.Errors;

public class InvalidRequestException extends Exception{

	private static final long serialVersionUID = 1L;
	private Errors errors;
    private String message;
	    public InvalidRequestException(String message, Errors errors) {
	        super(message);
	        this.errors = errors;
	    }

		public InvalidRequestException(String message) {
			this.setMessage(message);
		}

		public InvalidRequestException(String message,Throwable e) {
			super(message, e);
		}
		
		public InvalidRequestException(Throwable e) {
			super(e);
		}

		public Errors getErrors() {
			return errors;
		}

		public void setErrors(Errors errors) {
			this.errors = errors;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	    
}
