package com.barunsw.app.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.barunsw.app.mapper.UserMapper;
import com.barunsw.framework.utils.EncryptionUtil;

@Service
public class UserService {
	
	private final UserMapper userMapper;
	
	public UserService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public List<UserVo> getUserList() throws Exception {
		return userMapper.getUserList();
	}
	
	public UserVo getUser(UserVo userVo) throws Exception {
		return userMapper.getUser(userVo);
	}

	public int save(UserVo userVo) throws Exception {
		// 비밀번호 암호화해야함.
		userVo.setUserPassword(EncryptionUtil.encryptSHA512(userVo.getUserPassword()));
		return userMapper.save(userVo);
	}
}
