package com.barunsw.app.example;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class ApplicationVo {

	private int id;
	private String name;
	private String content;
	private String date;
	private String platform;
	private String url;
	private String image;
	
}
