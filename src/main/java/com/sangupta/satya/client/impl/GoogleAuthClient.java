/**
 *
 * satya - social authentication framework
 * Copyright (c) 2014, Sandeep Gupta
 * 
 * http://www.sangupta/projects/satya
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

package com.sangupta.satya.client.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.service.OAuth2ServiceImpl;
import com.sangupta.jerry.oauth.service.impl.GoogleOAuthServiceImpl;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.BaseAuthenticatedUser;
import com.sangupta.satya.user.UserProfile;

/**
 * 
 * @author sangupta
 *
 */
public class GoogleAuthClient extends BaseAuthClient {
	
	public static final String OAUTH2_ENDPOINT = "https://accounts.google.com/o/oauth2/auth";
	
	public GoogleAuthClient(KeySecretPair pair) {
		super(new GoogleOAuthServiceImpl(pair), "email profile");
	}

	public AuthenticatedUser verifyUser(HttpServletRequest request) {
		// extract the code from the request parameter
		String errorCode = request.getParameter("error");
		if(AssertUtils.isNotEmpty(errorCode)) {
			// TODO: make checked exceptions so that user's know what's happening
			return null;
		}
		
		// find the actual code
		String code = request.getParameter("code");
		
		if(AssertUtils.isEmpty(code)) {
			throw new IllegalArgumentException("The request does not appear to be a valid Google request");
		}
		
		System.out.println("Using google verification code: " + code);
		// obtain the authorization code
		String response = ((OAuth2ServiceImpl) this.service).getAuthorizationResponse(code);
		if(AssertUtils.isEmpty(response)) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = GsonUtils.getGson().fromJson(response, Map.class);
		
    	return new BaseAuthenticatedUser(map.get("access_token"), "", map.get("refresh_token"), 3600, this);
	}

	public boolean signOut() {
		return false;
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = this.getUsingJson(accessPair, url, Map.class);
		
		UserProfile profile = new UserProfile();
		profile.setUserID(map.get("id"));
		profile.setEmail(map.get("email"));
		profile.setFullName(map.get("name"));
		profile.setFirstName(map.get("given_name"));
		profile.setLastName(map.get("family_name"));
		profile.setProfileLink(map.get("link"));
		profile.setProfileImageURL(map.get("picture"));
		profile.setGender(map.get("gender"));
		
		return profile;
	}

}
