/**
 *
 * satya - social authentication framework
 * Copyright (c) 2014, Sandeep Gupta
 * 
 * http://sangupta.com/projects/satya
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

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
import com.sangupta.satya.client.impl.TwitterAuthClient;

/**
 * Example to test {@link TwitterAuthClient}
 * 
 * @author sangupta
 * @since 1.0
 */
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
			UserProfile profile = user.fetchUserProfile();
			System.out.println("User Profile: " + profile);
		}
		
		WebRequest wr = WebInvoker.getWebRequest("https://api.twitter.com/1.1/account/verify_credentials.json", WebRequestMethod.GET);
		user.signRequest(wr);
		WebResponse wrs = WebInvoker.executeSilently(wr);
		if(wrs == null) {
			System.out.println("null response");
			return;
		}
		
		System.out.println(wrs.trace());
		System.out.println(wrs.getContent());
	}

}
