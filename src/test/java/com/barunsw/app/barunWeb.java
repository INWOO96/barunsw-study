package com.barunsw.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class barunWeb {
	@Autowired
	DefaultListableBeanFactory defaultListableBeanFactory;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	void test() {
		System.out.println("barun");
		
		if ( applicationContext != null ) {
			for ( String bean : applicationContext.getBeanDefinitionNames()) {
				System.out.println("bean : " + bean);
			}
		}
		
		
	}

}
