package com.barunsw.app.login;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barunsw.app.user.UserService;
import com.barunsw.app.user.UserVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController {
	
	private final UserService userService;
	
	public LoginController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/signUp")
	public String checkUser(@Param("id") String id) {
		log.info("SignUp: checkUser = {}", id);
		String result = "ok";
		try {
			UserVo user = userService.getUser(new UserVo().setUserId(id));
			if (user != null) {
				result = null;
			}
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		
		return result;
	}
	
	@PostMapping("/signUp")
	public String regist(@ModelAttribute UserVo userVo) {
		log.info("SingUp UserInfo={}", userVo);
		int result = 0;

		try {
			result = userService.save(userVo);
		} 
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		
		return String.valueOf(result);
	}
}
