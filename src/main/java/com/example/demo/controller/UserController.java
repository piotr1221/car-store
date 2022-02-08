package com.example.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String getRegisterForm(User user) {
		return "register";
	}
	
	@PostMapping("")
	public String createUser(@Valid User user, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "register";
		}
		
		if (userService.createUser(user) == null) {
			model.addAttribute("error", "Username already in use");
			return "register";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String getLoginForm(User user) {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user, Errors errors, Model model) {
		if (userService.login(user) == null) {
			model.addAttribute("error", "Invalid credentials");
			return "login";
		}
		
		return "redirect:/home";
	}
}
