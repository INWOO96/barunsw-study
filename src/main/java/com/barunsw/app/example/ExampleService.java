package com.barunsw.app.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barunsw.app.mapper.ExampleMapper;
import com.barunsw.framework.garbege.GenerateDBData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExampleService {

	private ExampleMapper apiDao;
	
	@Autowired
	public ExampleService(ExampleMapper apiDao) {
		this.apiDao = apiDao;
	}
	
	public MySelfVo selectAboutMe() {
		log.info("Called select About Me");
		
		MySelfVo mySelf = null;
		try {
			mySelf = apiDao.selectMySelf();
			List<ResumeVo> resumes = apiDao.selectMyResume();
			
			if (resumes.isEmpty()) {
				apiDao.insertResumes(GenerateDBData.getResumeData());
				resumes = apiDao.selectMyResume();
			}
			
			mySelf.setResumes(resumes);
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		
		return mySelf;
	}
	
	public List<ApplicationVo> selectApplicationList() {
		List<ApplicationVo> list = apiDao.selectApplication();
		
		try {
			if (list.size() == 0) {
				log.info("INSERT Application Data!");
				apiDao.insertApplication(GenerateDBData.getGarbegeData());
				list = apiDao.selectApplication();
			}
			
		}
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		
		return list;
	}
	
	public List<TodoVo> selectTodoList() {
		return apiDao.selectTodoList();
	}
	
	public int insertTodo(TodoVo todo) {
		return apiDao.insertTodo(todo);
	}
	
	public int updateTodo(TodoVo todo) {
		return apiDao.updateTodo(todo);
	}
	
	public int deleteTodo(TodoVo todo) {
		return apiDao.deleteTodo(todo);
	}
	
	public List<PostVo> selectPostList() {
		List<PostVo> postList = apiDao.selectPostList();
		if (postList.isEmpty()) {
			apiDao.insertPost(GenerateDBData.getBlogPost());
			postList = apiDao.selectPostList();
		}
		
		return postList;
	}
	
	public PostVo selectOnePost(int id) {
		return apiDao.selectOnePost(id);
	}
	
	public int insertPost(PostVo post) {
		return apiDao.insertPost(post);
	}
	
	public int updatePost(PostVo post) {
		return apiDao.updatePost(post);
	}
	
	public int deletePost(PostVo post) {
		return apiDao.deletePost(post);
	}
}

