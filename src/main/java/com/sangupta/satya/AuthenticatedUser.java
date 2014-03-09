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

package com.sangupta.satya;

import com.sangupta.jerry.http.WebRequest;
import com.sangupta.satya.user.UserProfile;

/**
 * 
 * @author sangupta
 *
 */
public interface AuthenticatedUser {

	/**
	 * Get the user profile.
	 * 
	 * @return
	 */
	public UserProfile getUserProfile();

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
