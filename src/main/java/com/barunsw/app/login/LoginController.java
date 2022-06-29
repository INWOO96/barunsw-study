package com.barunsw.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.barunsw.app.user.UserService;
import com.barunsw.app.user.UserVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "login/login";
	}
	
	@GetMapping("/login/{userId}")
	public String login(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId);
		return "login/login";
	}
	
	@GetMapping("/signUp")
	public String signUp(@ModelAttribute UserVo userVo) {
		return "/login/signUp";
	}
	
	@PostMapping("/signUp")
	public String regist(@ModelAttribute UserVo userVo, Model model) {
		int result = 0;
		
		try {
			result = userService.save(userVo);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		model.addAttribute("result", result);
		
		return String.format("%s/%s", "redirect:/login", userVo.getUserId());
	}
}
