package com.barunsw.app.example;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostVo {
	
	private int id;
	private String title;
	private String post;
}