package com.sangupta.satya.examples;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.satya.ApiKeys;
import com.sangupta.satya.AuthConfig;
import com.sangupta.satya.AuthManager;
import com.sangupta.satya.AuthPermissions;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.MockHttpServletRequest;
import com.sangupta.satya.user.UserProfile;

public class TestTwitterClient {
	
	public static void main(String[] args) {
		AuthConfig config = new AuthConfig();
		
		KeySecretPair twitter = new KeySecretPair(ApiKeys.Twitter, ApiKeys.TwitterSecret);
		config.addConfig(AuthProvider.Twitter, twitter);
		
		AuthManager.loadConfig(config);
		
		String loginURL = AuthManager.getAuthRedirectURL(AuthProvider.Twitter, AuthPermissions.DEFAULT, "oob");
		System.out.println("Login URL: " + loginURL);
		
		String code = ConsoleUtils.readLine("code: ", true);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("code", code);
		AuthenticatedUser user = AuthManager.authenticateUser(request, loginURL);
		System.out.println("User: " + user);

		if(user != null) {
			UserProfile profile = user.getUserProfile();
			System.out.println("User Profile: " + profile);
		}
	}

}
