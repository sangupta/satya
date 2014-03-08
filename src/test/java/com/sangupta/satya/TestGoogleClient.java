package com.sangupta.satya;

import com.sangupta.jerry.oauth.domain.KeySecretPair;

public class TestGoogleClient {
	
	public static void main(String[] args) {
		AuthConfig config = new AuthConfig();
		config.addConfig(AuthProvider.Google, new KeySecretPair("", ""));
		AuthManager.loadConfig(config);
		
		String url = AuthManager.getAuthRedirectURL(AuthProvider.Google, AuthPermissions.DEFAULT, "http://opensource.brickred.com/social/auth");
		System.out.println(url);
	}

}
