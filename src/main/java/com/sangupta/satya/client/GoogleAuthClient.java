package com.sangupta.satya.client;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.service.impl.GoogleOAuthServiceImpl;
import com.sangupta.satya.AuthenticatedUser;

public class GoogleAuthClient extends BaseAuthClient {
	
	public static final String OAUTH2_ENDPOINT = "https://accounts.google.com/o/oauth2/auth";
	
	public GoogleAuthClient(KeySecretPair pair) {
		super(new GoogleOAuthServiceImpl(pair));
		this.service.setScope("email");
	}

	public AuthenticatedUser verifyUser(HttpServletRequest request) {
		return null;
	}

	public boolean signOut() {
		return false;
	}

}
