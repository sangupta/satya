package com.sangupta.satya.examples;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.satya.AuthConfig;
import com.sangupta.satya.AuthManager;
import com.sangupta.satya.AuthPermissions;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.ApiKeys;
import com.sangupta.satya.MockHttpServletRequest;
import com.sangupta.satya.user.UserProfile;

public class TestGoogleClient {
	
	public static void main(String[] args) {
		String redirectURL = "http://opensource.brickred.com/social/auth";
		
		AuthConfig config = new AuthConfig();
		
		KeySecretPair google = new KeySecretPair(ApiKeys.Google, ApiKeys.GoogleSecret);
		config.addConfig(AuthProvider.Google, google);
		
		AuthManager.loadConfig(config);
		
		String url = AuthManager.getAuthRedirectURL(AuthProvider.Google, AuthPermissions.DEFAULT, redirectURL);
		System.out.println(url);
		
		String code = ConsoleUtils.readLine("code: ", true);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("code", code);
		AuthenticatedUser user = AuthManager.authenticateUser(request, redirectURL);
		System.out.println("User: " + user);
		
		if(user != null) {
			UserProfile profile = user.getUserProfile();
			System.out.println(profile);
		}
	}

}
