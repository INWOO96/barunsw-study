package com.barunsw.app.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString(exclude = "userPassword")
@Accessors(chain = true)
public class UserVo {
	private String userId;
	private String userPassword;
	private String phone;
	private String email;
	
	public UserVo() {}
	public UserVo(String userId) {
		this.setUserId(userId);
	}
}
