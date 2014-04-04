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

package com.sangupta.satya.user;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.util.StringUtils;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.client.AuthClient;

/**
 * Base implementation of the {@link AuthenticatedUser} that provides
 * much common functionality around all social networks.
 *  
 * @author sangupta
 * @since 1.0
 */
public class BaseAuthenticatedUser implements AuthenticatedUser {

	/**
	 * The access token obtained from the server
	 */
	private KeySecretPair userAccessPair;
	
	/**
	 * The refresh token obtained from the server
	 */
	private String userRefreshToken;
	
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
	 * @param accessToken
	 * @param accessSecret
	 * @param refreshToken
	 * @param expiresIn
	 * @param authClient
	 */
	public BaseAuthenticatedUser(String accessToken, String accessSecret, String refreshToken, String expiresIn, AuthClient authClient) {
		this(accessToken, accessSecret, refreshToken, StringUtils.getLongValue(expiresIn, 3600), authClient);
	}
	
	/**
	 * 
	 * @param accessToken
	 * @param refreshToken
	 * @param expiry
	 */
	public BaseAuthenticatedUser(String accessToken, String accessSecret, String refreshToken, long expiresIn, AuthClient authClient) {
		this.userAccessPair = new KeySecretPair(accessToken, accessSecret);
		this.userRefreshToken = refreshToken;
		this.expiry = System.currentTimeMillis() + expiresIn;
		
		this.authClient = authClient;
	}
	
	@Override
	public UserProfile getUserProfile() {
		return this.authClient.getUserProfile(this.userAccessPair);
	}
	
	@Override
	public void signOut() {
		this.userAccessPair = null;
		this.userRefreshToken = null;
		this.expiry = 0;
		this.authClient.signOut();
	}

	@Override
	public void signRequest(WebRequest request) {
		this.authClient.signRequest(this.userAccessPair, request);
	}
	
	/**
	 * @return the userAccessPair
	 */
	@Override
	public KeySecretPair getUserAccessPair() {
		return userAccessPair;
	}

	// Usual accessors follow

	/**
	 * @return the userRefreshToken
	 */
	public String getUserRefreshToken() {
		return userRefreshToken;
	}

	/**
	 * @return the expiry
	 */
	public long getExpiry() {
		return expiry;
	}

}
