package com.barunsw.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.barunsw.app.example.ApplicationVo;
import com.barunsw.app.example.MySelfVo;
import com.barunsw.app.example.PostVo;
import com.barunsw.app.example.ResumeVo;
import com.barunsw.app.example.TodoVo;

@Mapper
public interface ExampleMapper {
	MySelfVo selectMySelf();
	
	List<ResumeVo> selectMyResume();
	
	List<ApplicationVo> selectApplication();
	
	int insertResumes(List<ResumeVo> resumeList);
	
	int insertApplication(List<ApplicationVo> appList);
	
	List<TodoVo> selectTodoList();
	
	int insertTodo(TodoVo todo);
	
	int updateTodo(TodoVo todo);
	
	int deleteTodo(TodoVo todo);
	
	List<PostVo> selectPostList();
	
	PostVo selectOnePost(int id);
	
	int insertPost(PostVo post);
	
	int updatePost(PostVo post);
	
	int deletePost(PostVo post);
	
}
