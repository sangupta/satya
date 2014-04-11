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

package com.sangupta.satya.client;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.domain.TokenAndUrl;
import com.sangupta.jerry.oauth.service.OAuthService;
import com.sangupta.satya.AuthenticatedUser;

/**
 * Contract for all implementation specific authentication clients
 * that help developers directly work with strongly-typed objects
 * to invoke several APIs on behalf of the authenticating user.
 * 
 * @author sangupta
 *
 */
public interface AuthClient {
	
	/**
	 * Obtain the login URL that the user needs to be redirected to for
	 * gaining permissions.
	 * 
	 * @param successUrl
	 * @return
	 */
	public TokenAndUrl getLoginRedirectURL(String successUrl);
	
	/**
	 * Verify that a user did grant us permissions so that we can invoke
	 * further API calls on his/her behalf.
	 * 
	 * @param request
	 * @param redirectURL
	 * @return
	 */
	public AuthenticatedUser verifyUser(HttpServletRequest request, TokenAndUrl tokenAndUrl);
	
	/**
	 * 
	 * @param verifier
	 * @param redirectURL
	 * @return
	 */
	public AuthenticatedUser verifyUser(String verifier, TokenAndUrl tokenAndUrl);

	/**
	 * Sign-out this current user from the backend, if APIs are available.
	 * 
	 * @return
	 */
	public boolean signOut();

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
	
	/**
	 * Return the associated {@link OAuthService} being used.
	 * 
	 * @return
	 */
	public OAuthService getOAuthService();
	
}
