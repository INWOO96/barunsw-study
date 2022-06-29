package com.barunsw.app.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.barunsw.app.user.UserService;
import com.barunsw.app.user.UserVo;
import com.barunsw.framework.utils.EncryptionUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SecurityProvider implements AuthenticationManager {

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			UserVo userVo = userService.getUser(new UserVo(authentication.getName()));
			if ( userVo != null ) {
				String passwordParam = EncryptionUtil.encryptSHA512(authentication.getCredentials().toString());
				if ( userVo.getUserPassword().equals(passwordParam) ) {
			        List<GrantedAuthority> roles = AuthorityUtils.createAuthorityList("ADMIN");
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), roles);
					log.info("isAuthenticated : {}",token.isAuthenticated());
			        return token;
				}
				else {
					log.info("password is different.{}", passwordParam);
					return null;
				}
			}
			else {
				log.info("user not found.");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return null;
	}


}
