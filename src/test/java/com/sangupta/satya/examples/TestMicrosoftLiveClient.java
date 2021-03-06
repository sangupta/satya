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
import com.sangupta.jerry.oauth.scope.MicrosoftLiveScopes;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.satya.ApiKeys;
import com.sangupta.satya.AuthConfig;
import com.sangupta.satya.AuthManager;
import com.sangupta.satya.AuthPermissions;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.MockHttpServletRequest;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.impl.MicrosoftLiveAuthClient;

/**
 * Example to test {@link MicrosoftLiveAuthClient}
 * 
 * @author sangupta
 * @since 1.0
 */
public class TestMicrosoftLiveClient {
	
	public static void main(String[] args) {
		String redirectURL = "http://opensource.brickred.com/social/auth";
		
		// create auth config
		AuthConfig config = new AuthConfig();
		
		// load values
		KeySecretPair microsoftLive = new KeySecretPair(ApiKeys.MicrosoftLive, ApiKeys.MicrosoftLiveSecret);
		config.addConfig(AuthProvider.MicrosoftLive, microsoftLive, MicrosoftLiveScopes.READ_USER_PROFILE);
		
		// initialize AuthManager
		AuthManager.loadConfig(config);
		
		// get redirect URL
		TokenAndUrl tokenAndUrl = AuthManager.getAuthRedirectURL(AuthProvider.MicrosoftLive, redirectURL, AuthPermissions.BASIC_PROFILE);
		System.out.println("Open the URL in browser: " + tokenAndUrl);
		
		// read code as provided by the browser
		String code = ConsoleUtils.readLine("code: ", true);
		
		// create a mock request to continue workflow
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("code", code);
		
		// get the user from the yahoo api
		AuthenticatedUser user = AuthManager.authenticateUser(AuthProvider.MicrosoftLive, request, tokenAndUrl);
		
		// print user details
		System.out.println(user);
		
		// print user profile - if we can
		if(user != null) {
			UserProfile profile = user.fetchUserProfile();
			System.out.println(profile);
		}
	}

}
