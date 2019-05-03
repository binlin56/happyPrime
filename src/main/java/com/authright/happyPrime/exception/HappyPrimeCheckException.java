package com.authright.happyPrime.exception;

import com.authright.happyPrime.constant.HappyPrimeCheckConstants;

public class HappyPrimeCheckException extends RuntimeException {
	
	int statusCode = HappyPrimeCheckConstants.FAILED_ERROR_CODE;
	String errCode = "Unknown";

	
	public HappyPrimeCheckException(String message) {
        super(message);
    }

    public HappyPrimeCheckException(String message, Throwable cause) {
        super(message, cause);
    }
    

    public HappyPrimeCheckException(Throwable cause) {
        super(cause);

    }
    public HappyPrimeCheckException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errCode = errorCode;
    }
    public HappyPrimeCheckException(String message,  String errCode) {
        super(message);
        this.errCode = errCode;
    }
    public HappyPrimeCheckException(String message,  int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
