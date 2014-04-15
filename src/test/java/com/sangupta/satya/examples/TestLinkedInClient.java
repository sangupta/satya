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
import com.sangupta.jerry.oauth.scope.LinkedInScopes;
import com.sangupta.jerry.util.ConsoleUtils;
import com.sangupta.satya.ApiKeys;
import com.sangupta.satya.AuthConfig;
import com.sangupta.satya.AuthManager;
import com.sangupta.satya.AuthPermissions;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.MockHttpServletRequest;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.impl.LinkedInAuthClient;

/**
 * Example to test {@link LinkedInAuthClient}
 * 
 * @author sangupta
 * @since 1.0
 */
public class TestLinkedInClient {

	public static void main(String[] args) {
		String redirectURL = "http://opensource.brickred.com/social/auth";
		
		AuthConfig config = new AuthConfig();
		
		KeySecretPair linkedIn = new KeySecretPair(ApiKeys.LinkedIn, ApiKeys.LinkedInSecret);
		config.addConfig(AuthProvider.LinkedIn, linkedIn, LinkedInScopes.BASIC_PROFILE);
		
		AuthManager.loadConfig(config);
		
		TokenAndUrl tokenAndUrl = AuthManager.getAuthRedirectURL(AuthProvider.LinkedIn, redirectURL, AuthPermissions.BASIC_PROFILE);
		System.out.println(tokenAndUrl);
		
		String code = ConsoleUtils.readLine("code: ", true);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("code", code);
		AuthenticatedUser user = AuthManager.authenticateUser(AuthProvider.LinkedIn, request, tokenAndUrl);
		System.out.println("User: " + user);
		
		if(user != null) {
			UserProfile profile = user.fetchUserProfile();
			System.out.println(profile);
		}
	}
	
}
