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

package com.sangupta.satya.user;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.client.AuthClient;

/**
 * 
 * @author sangupta
 *
 */
public class BaseAuthenticatedUser implements AuthenticatedUser {

	/**
	 * The access token obtained from the server
	 */
	private KeySecretPair accessPair;
	
	/**
	 * The refresh token obtained from the server
	 */
	private String refreshToken;
	
	/**
	 * The time in millis when the token expires
	 */
	private long expiry;
	
	/**
	 * The authentication client to use
	 */
	private AuthClient authClient;
	
	/**
	 * 
	 * @param accessToke
	 * @param refreshToken
	 * @param expiry
	 */
	public BaseAuthenticatedUser(String accessToken, String accessSecret, String refreshToken, long expiresIn, AuthClient authClient) {
		this.accessPair = new KeySecretPair(accessToken, accessSecret);
		this.refreshToken = refreshToken;
		this.expiry = System.currentTimeMillis() + expiresIn;
		
		this.authClient = authClient;
	}
	
	@Override
	public UserProfile getUserProfile() {
		return this.authClient.getUserProfile(this.accessPair);
	}
	
	@Override
	public void signOut() {
		this.accessPair = null;
		this.refreshToken = null;
		this.expiry = 0;
		this.authClient.signOut();
	}

	@Override
	public void signRequest(WebRequest request) {
		//
	}

}
