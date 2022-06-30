package com.barunsw.app.example;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResumeVo {

	private String title;
	private String content;
	private String url;
	private Date date;
	
}
