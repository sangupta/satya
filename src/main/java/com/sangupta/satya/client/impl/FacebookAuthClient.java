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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.UrlParamExtractor;
import com.sangupta.jerry.oauth.scope.FacebookScopes;
import com.sangupta.jerry.oauth.service.OAuth2ServiceImpl;
import com.sangupta.jerry.oauth.service.impl.FacebookOAuthServiceImpl;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.BaseAuthenticatedUser;
import com.sangupta.satya.user.UserProfile;

/**
 * 
 * @author sangupta
 *
 */
public class FacebookAuthClient extends BaseAuthClient {

	/**
	 * 
	 * @param keySecretPair
	 */
	public FacebookAuthClient(KeySecretPair keySecretPair) {
		super(new FacebookOAuthServiceImpl(keySecretPair), FacebookScopes.EMAIL);
	}

	@Override
	public AuthenticatedUser verifyUser(HttpServletRequest request, String redirectURL) {
		// extract the code from the request parameter
		String errorCode = request.getParameter("error");
		if(AssertUtils.isNotEmpty(errorCode)) {
			// TODO: make checked exceptions so that user's know what's happening
			return null;
		}
		
		// find the actual code
		String code = request.getParameter("code");
		return this.verifyUser(code, redirectURL);
	}
	
	@Override
	public AuthenticatedUser verifyUser(String verifier, String redirectURL) {
		if(AssertUtils.isEmpty(verifier)) {
			throw new IllegalArgumentException("The request does not appear to be a valid Google request");
		}
		
		System.out.println("Using facebook verification code: " + verifier);
		// obtain the authorization code
		String response = ((OAuth2ServiceImpl) this.service).getAuthorizationResponse(verifier, redirectURL);
		if(AssertUtils.isEmpty(response)) {
			return null;
		}
		
		System.out.println("facebook response: " + response);
		
		Map<String, String> map = new UrlParamExtractor().extractTokens(response);
    	return new BaseAuthenticatedUser(map.get("access_token"), "", map.get("refresh_token"), map.get("expires"), this);
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		String url = "https://graph.facebook.com/me";
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = this.getUsingJson(accessPair, url, Map.class);
		
		System.out.println(map);
		
		UserProfile profile = new UserProfile(AuthProvider.Facebook, map.get("id"));
		
		profile.setEmail(map.get("email"));
		profile.setFullName(map.get("name"));
		profile.setFirstName(map.get("first_name"));
		profile.setLastName(map.get("last_name"));
		
		try {
			String birthDay = map.get("birthday");
			if(AssertUtils.isNotBlank(birthDay)) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				Date bday = format.parse(birthDay);
				if(bday != null) {
					profile.setDateOfBirth(bday.getTime());
				}
			}
		} catch(Exception e) {
			
		}
		
		profile.setProfileLink(map.get("link"));
		profile.setProfileImageURL(map.get("picture"));
		profile.setGender(map.get("gender"));
		
		return profile;
	}

}
