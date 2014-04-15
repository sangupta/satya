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

import java.security.Principal;

import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.UserProfile;

/**
 * Abstract value object that holds all details of the user. This also
 * implements the {@link Principal} interface so that the obtained user can
 * directly be added to the <code>SecurityContext</code> implementations of Java
 * security frameworks.
 * 
 * This class needs to sub-classed for each OAuth implementation to support the
 * extra OAuth provider specific parameters.
 * 
 * @author sangupta
 * @since 1.0
 */
public abstract class BaseUserProfile implements UserProfile {
	
	/**
	 * The {@link AuthProvider} associated with this {@link UserProfile}
	 */
	private final AuthProvider authProvider;

	/**
	 * The email of the user
	 */
	protected String email;
	
	/**
	 * Construct an immutable {@link UserProfile} for given auth provider.
	 * 
	 * @param userID
	 * @param authProvider
	 */
	public BaseUserProfile(AuthProvider authProvider) {
		this.authProvider = authProvider;
	}
	
	@Override
	public String getName() {
		return this.getDisplayName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof UserProfile)) {
			return false;
		}
		
		if(this.getUserID() == null) {
			return false;
		}

		return this.getUserID().equals(((UserProfile) obj).getUserID());
	}
	
	@Override
	public int hashCode() {
		if(this.getUserID() == null) {
			return -1;
		}
		
		return this.getUserID().hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(1024);
		
		builder.append("[UserProfile: ");
		builder.append(this.authProvider.toString());
		builder.append("; ");
		builder.append(this.getDisplayName());
		builder.append("]");
		
		return builder.toString();
	}
	
	// Usual accessors follow

	/**
	 * @return the email
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the authProvider
	 */
	public AuthProvider getAuthProvider() {
		return authProvider;
	}

}
