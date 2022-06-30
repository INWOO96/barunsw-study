package com.barunsw.app.spring;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	/**
	 * Vue Router Hash 모드 사용시 해당 설정 없어도 됨
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
        		.addResourceLocations("classpath:/static/")
    			.resourceChain(true)
    			.addResolver(new PathResourceResolver() {
    				
    				@Override
    				protected Resource getResource(String resourcePath, Resource location) throws IOException {
    					Resource requestedResource = location.createRelative(resourcePath);
    					//Spring retrun page가 없는경우 index.html 호출 -> Vue router 동작
    					return requestedResource.exists() && requestedResource.isReadable() 
    							? requestedResource
    							: new ClassPathResource("/static/index.html");
					}
        });
	}
}
