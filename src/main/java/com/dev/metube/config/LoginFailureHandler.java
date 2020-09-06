package com.dev.metube.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String errMsg = "";
		
		if(exception instanceof BadCredentialsException
				|| exception instanceof InternalAuthenticationServiceException) {
			errMsg = "계정정보가 일치하지 않습니다.";
		} else {
			errMsg = "인증되지 않은 계정입니다.";
		}
		
		String defaultFailureUrl = request.getContextPath() + "/login";
		System.out.println(defaultFailureUrl);
        request.setAttribute("errMsg", errMsg);
        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
	}
}
