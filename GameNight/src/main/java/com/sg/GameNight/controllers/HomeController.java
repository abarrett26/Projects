/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.GameNight.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author 16128
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String showHomePage() {

        return "home";
    }
}
