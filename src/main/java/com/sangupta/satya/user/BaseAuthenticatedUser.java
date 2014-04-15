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

import java.util.Map;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.service.OAuthService;
import com.sangupta.jerry.util.StringUtils;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.AuthClient;

/**
 * Base implementation of the {@link AuthenticatedUser} that provides
 * much common functionality around all social networks.
 *  
 * @author sangupta
 * @since 1.0
 */
public abstract class BaseAuthenticatedUser implements AuthenticatedUser {

	/**
	 * The authentication client to use
	 */
	protected AuthClient authClient;
	
	/**
	 * The raw parameters obtained during the final call of OAuth authorization
	 * workflow
	 */
	protected Map<String, String> rawParameters;
	
	/**
	 * The access token obtained from the server
	 */
	protected KeySecretPair userAccessPair;
	
	/**
	 * The refresh token obtained from the server
	 */
	protected String userRefreshToken;
	
	/**
	 * The time in millis when the token expires
	 */
	protected long expiry;
	
	/**
	 * The {@link UserProfile} for the currently authenticated user.
	 * 
	 */
	protected UserProfile userProfile;
	
	/**
	 * Create a new instance of {@link BaseAuthenticatedUser} and initialize the
	 * minimum parameters
	 * 
	 * @param authClient
	 *            the {@link AuthClient} associated with this user
	 * 
	 * @param rawParameters
	 *            the raw parameters obtained from the authorization call
	 */
	public BaseAuthenticatedUser(AuthClient authClient, Map<String, String> rawParameters) {
		this.authClient = authClient;
		this.rawParameters = rawParameters;
		
		// inferred parameters
		OAuthService service = this.authClient.getOAuthService();
		
		this.userAccessPair = new KeySecretPair(rawParameters.get(service.getAccessTokenParamName()), rawParameters.get(service.getAccessTokenSecretParamName()));
		if(service.getRefreshTokenParamName() != null) {
			this.userRefreshToken = rawParameters.get(service.getRefreshTokenParamName());
		}
		
		if(service.getAccessTokenExpiryParamName() != null) {
			this.expiry = StringUtils.getLongValue(rawParameters.get(service.getAccessTokenExpiryParamName()), getDefaultTokenExpiryTime());
		}
	}
	
	/**
	 * Return the URL to be used for fetching the user profile.
	 *  
	 * @return
	 */
	public abstract String getUserProfileURL();
	
	/**
	 * Return the class to be used for populating user profile.
	 * 
	 * @return
	 */
	public abstract Class<? extends UserProfile> getUserProfileClass();
	
	/**
	 * 
	 * @return
	 */
	public long getDefaultTokenExpiryTime() {
		return 3600;
	}
	
	/**
	 * Get the user profile - uses cache if value was previously fetched
	 * from server.
	 * 
	 */
	@Override
	public final UserProfile getUserProfile() {
		if(this.userProfile == null) {
			return this.fetchUserProfile();
		}
		
		return this.userProfile;
	}
	
	/**
	 * Fetch the user profile from the remote server even if available in 
	 * client cache
	 * 
	 */
	@Override
	public UserProfile fetchUserProfile() {
		UserProfile profile = this.authClient.getUsingJson(this.userAccessPair, getUserProfileURL(), getUserProfileClass());
		this.userProfile = profile;
		return profile;
	}
	
	/**
	 * Sign-out the current user from the remote server.
	 * 
	 */
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
