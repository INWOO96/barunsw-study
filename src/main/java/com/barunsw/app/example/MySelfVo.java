package com.barunsw.app.example;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MySelfVo {
	private String name;
	private String email;
	private List<ResumeVo> resumes;
}
