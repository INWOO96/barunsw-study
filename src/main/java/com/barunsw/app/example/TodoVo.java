package com.barunsw.app.example;

import com.barunsw.framework.constants.RequestType;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TodoVo {

	private int id;
	private String job;
	private String date;
	private String completed;
	private RequestType request;
	
}
