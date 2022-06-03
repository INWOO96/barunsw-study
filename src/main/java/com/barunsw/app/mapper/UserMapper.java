package com.barunsw.app.mapper;

import java.util.List;
import com.barunsw.app.user.UserVo;

//@Mapper
public interface UserMapper {
	public List<UserVo> getUserList() throws Exception;
}
