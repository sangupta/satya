package com.sangupta.satya.client;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.satya.AuthenticatedUser;

public interface AuthClient {
	
	public String getLoginRedirectURL(String successUrl);
	
	public AuthenticatedUser verifyUser(HttpServletRequest request);

	public boolean signOut();
	
}
