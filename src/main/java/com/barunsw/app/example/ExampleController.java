package com.barunsw.app.example;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ExampleController {

	@Autowired
	private ExampleService apiService;
	
	@GetMapping("/api/about-me")
	public MySelfVo getAboutMe(final HttpServletRequest request) {
		log.info("Called About me");
		
		return apiService.selectAboutMe();
	}
	
	@GetMapping("/api/applications")
	public List<ApplicationVo> getApplicationList() {
		log.info("Called Applications");
		
		return apiService.selectApplicationList();
	}
	
	@GetMapping("/api/getTodoList")
	public List<TodoVo> getTodoList() {
		log.info("Called getTodoList");
		
		return apiService.selectTodoList();
	}
	
	@PostMapping("/api/postTodo")
	public void postTodo(@RequestBody TodoVo todo) throws Exception {
		log.info("Called postTodo {}", todo);
		
		switch (todo.getRequest()) {
		case I:
			apiService.insertTodo(todo);
			break;
		case U:
			apiService.updateTodo(todo);
			break;
		case D:
			apiService.deleteTodo(todo);
			break;
		default:
			throw new Exception(todo.toString());
		}
	}
	
	@GetMapping("/api/blog")
	public List<PostVo> getPostList() {
		return apiService.selectPostList();
	}
}
