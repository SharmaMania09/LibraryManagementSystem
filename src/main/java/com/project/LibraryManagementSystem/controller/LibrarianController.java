package com.project.LibraryManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/librarian")
public class LibrarianController 
{
    @GetMapping("/dashboard")
    public ModelAndView getMethodName() 
    {
        ModelAndView mv = new ModelAndView("librarian-dashboard");
        return mv;
    }
}
