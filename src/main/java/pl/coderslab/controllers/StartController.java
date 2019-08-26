package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartController {

    @RequestMapping(path="/", produces = "text/html; charset=UTF-8")
    public String mainPage() {
        return "index";
    }
}
