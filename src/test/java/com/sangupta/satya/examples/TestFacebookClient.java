package com.sangupta.satya.examples;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.domain.TokenAndUrl;
import com.sangupta.jerry.oauth.scope.FacebookScopes;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.satya.ApiKeys;
import com.sangupta.satya.AuthConfig;
import com.sangupta.satya.AuthManager;
import com.sangupta.satya.AuthPermissions;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.MockHttpServletRequest;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.impl.FacebookAuthClient;

/**
 * Example to test {@link FacebookAuthClient}
 * 
 * @author sangupta
 * @since 1.0
 */
public class TestFacebookClient {
	
	public static void main(String[] args) {
		String redirectURL = "http://fbuserrecommend.appspot.com/auth";
		
		AuthConfig config = new AuthConfig();
		
		KeySecretPair facebook = new KeySecretPair(ApiKeys.Facebook, ApiKeys.FacebookSecret);
		config.addConfig(AuthProvider.Facebook, facebook, FacebookScopes.EMAIL);
		
		AuthManager.loadConfig(config);
		
		TokenAndUrl tokenAndUrl = AuthManager.getAuthRedirectURL(AuthProvider.Facebook, redirectURL, AuthPermissions.BASIC_PROFILE);
		System.out.println(tokenAndUrl);
		
		String code = ConsoleUtils.readLine("code: ", true);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("code", code);
		AuthenticatedUser user = AuthManager.authenticateUser(AuthProvider.Facebook, request, tokenAndUrl);
		System.out.println(user);

		if(user != null) {
			UserProfile profile = user.fetchUserProfile();
			System.out.println("UserProfile: " + profile);
		}
	}

}
