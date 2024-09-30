package io.technicaAssigment.shoeHeaven.controller.web;

import io.technicaAssigment.shoeHeaven.exceptions.NoMoreShoeException;
import io.technicaAssigment.shoeHeaven.exceptions.ShoeNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionController {

    @ExceptionHandler(NoMoreShoeException.class)
    public String handleNoMoreShoeException(NoMoreShoeException ex, Model model){
        model.addAttribute("message", ex.getMessage());
        return "error/no-more-shoe";
    }

    @ExceptionHandler(ShoeNotFoundException.class)
    public String handleShoeNotFoundException(ShoeNotFoundException ex, Model model){
        model.addAttribute("message", ex.getMessage());
        return "error/book-not-found";
    }
}
