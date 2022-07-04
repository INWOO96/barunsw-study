package com.barunsw.app.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/assets/**", "favicon.ico");
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .antMatchers( "/login/**", "/signUp").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
            .antMatchers("/**").hasAuthority("ADMIN")
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/loginProc")
            .successHandler(new LoginSuccessHandler())
            .failureHandler(new LoginFailureHandler())
	        .and()
	        .logout()
	        .logoutUrl("/logout")
	        .logoutSuccessUrl("/")
	        .and()
	        .csrf().disable();
        
        return http.build();
    }
	
	@Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new SecurityProvider();
    }
}