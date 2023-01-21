package com.test.task.exception;

public class NotFoundFilterException extends RuntimeException{

    private static String message = "Not found filter: ";
    public NotFoundFilterException(String value) {
        super(message + value);
    }
}
