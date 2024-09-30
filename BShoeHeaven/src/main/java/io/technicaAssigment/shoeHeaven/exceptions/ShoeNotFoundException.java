package io.technicaAssigment.shoeHeaven.exceptions;

import io.technicaAssigment.shoeHeaven.error.ErrorMessage;

public class ShoeNotFoundException extends RuntimeException {
    public ShoeNotFoundException() {
        super(ErrorMessage.SHOE_NOT_FOUND);
    }
}
