package io.technicaAssigment.shoeHeaven.exceptions;


import io.technicaAssigment.shoeHeaven.error.ErrorMessage;

public class NoMoreShoeException extends RuntimeException {
    public NoMoreShoeException() {
        super(ErrorMessage.NO_SHOE);
    }
}
