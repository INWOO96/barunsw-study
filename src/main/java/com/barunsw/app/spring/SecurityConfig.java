package com.barunsw.app.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .antMatchers( "/login", "/resources/**").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
            .antMatchers("/app/**").hasAuthority("ADMIN")
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/loginProc")
            .defaultSuccessUrl("/app/user/list")
            .failureUrl("/login") // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다.
	        .and()
	        .csrf().disable();
        
        return http.build();
    }
	
	@Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new SecurityProvider();
    }
}