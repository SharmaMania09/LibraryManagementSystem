package com.project.LibraryManagementSystem.controller;

import com.project.LibraryManagementSystem.model.User;
import com.project.LibraryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/users")
public class UserController 
{
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) 
    {
        userService.saveUser(user);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginPage() 
    {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) 
    {
        String token = userService.verifyUser(user);

        if ("Failed".equals(token)) 
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        //
        return ResponseEntity.ok(token);
    }
    

    @GetMapping("/dashboard")
    public ModelAndView getDashboardView() 
    {
        return new ModelAndView("user-dashboard");
    }
    
}
