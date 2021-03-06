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

package com.sangupta.satya;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.oauth.domain.KeySecretPair;

/**
 * Contract for a user who is authenticated in one of the social
 * networks and provides convenience methods to access various functionality
 * around user actions.
 * 
 * @author sangupta
 * @since 1.0
 */
public interface AuthenticatedUser {
	
	/**
	 * Get the user specific key-secret pair obtained from the server
	 * which can be persisted, and used afterwards to make calls to the
	 * server on user's behalf.
	 * 
	 * @return
	 */
	public KeySecretPair getUserAccessPair();
	
	/**
	 * Get the last user profile. Hit the webservice if none is available,
	 * and store in in-memory cache, or use from cache if available.
	 * 
	 * @return
	 */
	public UserProfile getUserProfile();

	/**
	 * Get the latest user profile without using the in-memory cache.
	 * This will shoot the webservice call everytime.
	 * 
	 * @return
	 */
	public UserProfile fetchUserProfile();

	/**
	 * Sign the request.
	 * 
	 * @param request
	 */
	public void signRequest(WebRequest request);

	/**
	 * Sign out the user.
	 * 
	 */
	public void signOut();
	
}
