package com.barunsw.app.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.info("onAuthenticationSuccess name:{}, crefentials:{}, details:{}, principal:{}"
				, authentication.getName(), authentication.getCredentials(), authentication.getDetails(), authentication.getPrincipal());
		
		HttpSession httpSession = request.getSession();
		
		response.setHeader("auth", httpSession.getId());
		response.setHeader("name", authentication.getName());
	}
}

	