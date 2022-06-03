package com.barunsw.app.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barunsw.app.mapper.UserMapper;

@Service
public class UserService {
	
	private final UserMapper userMapper;
	
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public List<UserVo> getUserList() throws Exception {
		return userMapper.getUserList();
	}
}
