package com.barunsw.app.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class UserController {
	
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/app/user/list")
	public Map<String, Object> getUserList() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserVo> userInfo = null;
		
		try {
			userInfo = userService.getUserList();
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		
		resultMap.put("result", userInfo);
		
		return resultMap;
	}
}
