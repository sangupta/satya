package com.sangupta.satya.examples;

import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.http.WebRequestMethod;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
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
		String redirectURL = "http://opensource.brickred.com/social/auth";
		
		AuthConfig config = new AuthConfig();
		
		KeySecretPair facebook = new KeySecretPair(ApiKeys.Facebook, ApiKeys.FacebookSecret);
		config.addConfig(AuthProvider.Facebook, facebook);
		
		AuthManager.loadConfig(config);
		
		String url = AuthManager.getAuthRedirectURL(AuthProvider.Facebook, AuthPermissions.DEFAULT, redirectURL);
		System.out.println(url);
		
		String code = ConsoleUtils.readLine("code: ", true);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("code", code);
		AuthenticatedUser user = AuthManager.authenticateUser(request, redirectURL);
		System.out.println(user);
		
		UserProfile profile = user.getUserProfile();
		System.out.println(profile);
		
		WebRequest wr = WebInvoker.getWebRequest("https://graph.facebook.com/me/likes", WebRequestMethod.GET);
		user.signRequest(wr);
		WebResponse wrs = WebInvoker.executeSilently(wr);
		if(wrs != null) {
			wrs.trace();
			System.out.println(wrs.getContent());
		}
	}

}
