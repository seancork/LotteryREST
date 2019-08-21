package com.seanoneill.lottery.lottery.exceptions;

public class GeneralCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GeneralCustomException(String message) {

        super(message);

    }
}