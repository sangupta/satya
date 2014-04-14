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

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.domain.TokenAndUrl;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.satya.ApiKeys;
import com.sangupta.satya.AuthConfig;
import com.sangupta.satya.AuthManager;
import com.sangupta.satya.AuthPermissions;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.MockHttpServletRequest;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.impl.YahooAuthClient;

/**
 * Example to test {@link YahooAuthClient}
 * 
 * @author sangupta
 * @since 1.0
 */
public class TestYahooClient {
	
	public static void main(String[] args) throws Exception {
		String redirectURL = "http://opensource.brickred.com/social/auth";

		// create auth config
		AuthConfig config = new AuthConfig();
		
		// load values
		KeySecretPair yahoo = new KeySecretPair(ApiKeys.Yahoo, ApiKeys.YahooSecret);
		config.addConfig(AuthProvider.Yahoo, yahoo);
		
		// initialize AuthManager
		AuthManager.loadConfig(config);
		
		// get redirect URL
		TokenAndUrl loginURL = AuthManager.getAuthRedirectURL(AuthProvider.Yahoo, AuthPermissions.DEFAULT, redirectURL);
		System.out.println("Open the URL in browser: " + loginURL);
		
		// read code as provided by the browser
		String code = ConsoleUtils.readLine("code: ", true);
		
		// create a mock request to continue workflow
		MockHttpServletRequest request1 = new MockHttpServletRequest();
		request1.addParameter("code", code);
		
		// get the user from the yahoo api
		AuthenticatedUser user = AuthManager.authenticateUser(request1, loginURL);
		
		// print user details
		System.out.println(user);
		
		// print user profile - if we can
		if(user != null) {
			UserProfile profile = user.fetchUserProfile();
			System.out.println(profile);
		}
	}

}
