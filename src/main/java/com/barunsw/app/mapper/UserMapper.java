package com.barunsw.app.mapper;

import java.util.List;
import com.barunsw.app.user.UserVo;

public interface UserMapper {
	public List<UserVo> getUserList() throws Exception;
	public UserVo getUser(UserVo userVo) throws Exception;
	public int save(UserVo userVo) throws Exception;
}
