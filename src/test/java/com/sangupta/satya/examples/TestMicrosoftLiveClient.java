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

public class TestMicrosoftLiveClient {
	
	public static void main(String[] args) {
		String redirectURL = "http://opensource.brickred.com/social/auth";
		
		// create auth config
		AuthConfig config = new AuthConfig();
		
		// load values
		KeySecretPair yahoo = new KeySecretPair(ApiKeys.MicrosoftLive, ApiKeys.MicrosoftLiveSecret);
		config.addConfig(AuthProvider.MicrosoftLive, yahoo);
		
		// initialize AuthManager
		AuthManager.loadConfig(config);
		
		// get redirect URL
		String url = AuthManager.getAuthRedirectURL(AuthProvider.MicrosoftLive, AuthPermissions.DEFAULT, redirectURL);
		System.out.println("Open the URL in browser: " + url);
		
		// read code as provided by the browser
		String code = ConsoleUtils.readLine("code: ", true);
		
		// create a mock request to continue workflow
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("code", code);
		
		// get the user from the yahoo api
		AuthenticatedUser user = AuthManager.authenticateUser(request, redirectURL);
		
		// print user details
		System.out.println(user);
		
		// print user profile - if we can
		if(user != null) {
			UserProfile profile = user.getUserProfile();
			System.out.println(profile);
		}
	}

}
