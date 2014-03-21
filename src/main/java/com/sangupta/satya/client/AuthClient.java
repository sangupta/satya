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

package com.sangupta.satya.client;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.user.UserProfile;

/**
 * 
 * @author sangupta
 *
 */
public interface AuthClient {
	
	public String getLoginRedirectURL(String successUrl);
	
	public AuthenticatedUser verifyUser(HttpServletRequest request, String redirectURL);

	public boolean signOut();

	/**
	 * Fetch the user's profile.
	 * 
	 * @return
	 */
	public UserProfile getUserProfile(KeySecretPair accessPair);
	
	/**
	 * Make a HTTP GET request and return its response.
	 *  
	 * @param url
	 * @return
	 */
	public <T> T getUsingJson(KeySecretPair accessPair, String url, Class<T> clazz);

	/**
	 * Sign the given {@link WebRequest} with OAuth credentials.
	 * 
	 * @param accessPair
	 * @param request
	 */
	public void signRequest(KeySecretPair accessPair, WebRequest request);
	
}
