package io.technicaAssigment.shoeHeaven.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String home(){
        return "redirect:/home";
    }
}
