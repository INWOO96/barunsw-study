package com.barunsw.app.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserVo {
	private String userId;
	private String userPassword;
	
	public UserVo() {}
	public UserVo(String userId) {
		this.setUserId(userId);
	}
}