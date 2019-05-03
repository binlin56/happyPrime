package com.authright.happyPrime.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CheckResult {

	private long number;
    private NumberType type;
    private String message;
   
    public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}
    public NumberType getType() {
        return type;
    }

    public void setType(NumberType status) {
        this.type = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "CheckResult [number=" + number + ", type=" + type + ", message=" + message + "]";
	}

}
