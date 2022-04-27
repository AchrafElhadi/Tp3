package com.example.secondproj.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/403")
    public String AccesDeniedPage()
    {
        return "403";
    }
}
