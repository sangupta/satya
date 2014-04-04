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

/**
 * The value object that holds all details of the user. This also implements the
 * {@link Principal} interface so that the obtained user can directly be added
 * to the <code>SecurityContext</code> implementations of Java security
 * frameworks.
 * 
 * @author sangupta
 * @since 1.0
 */
public class UserProfile implements Principal {
	
	private final String userID;
	
	private final AuthProvider authProvider;

	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private long dateOfBirth;
	
	private String country;
	
	private String fullName;
	
	private String displayName;
	
	private String gender;
	
	private String profileLink;
	
	private String profileImageURL;
	
	/**
	 * Construct an immutable {@link UserProfile} for given auth provider.
	 * 
	 * @param userID
	 * @param authProvider
	 */
	public UserProfile(AuthProvider authProvider, String userID) {
		this.authProvider = authProvider;
		this.userID = userID;
	}
	
	@Override
	public String getName() {
		if(this.displayName != null) {
			return this.displayName;
		}
		
		if(this.fullName != null) {
			return this.fullName;
		}
		
		return this.firstName;
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
		
		if(this.userID == null) {
			return false;
		}

		return this.userID.equals(((UserProfile) obj).userID);
	}
	
	@Override
	public int hashCode() {
		if(this.userID == null) {
			return -1;
		}
		
		return this.userID.hashCode();
	}
	
	// Usual accessors follow

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @return the email
	 */
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public long getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the profileImageURL
	 */
	public String getProfileImageURL() {
		return profileImageURL;
	}

	/**
	 * @param profileImageURL the profileImageURL to set
	 */
	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}

	/**
	 * @return the profileLink
	 */
	public String getProfileLink() {
		return profileLink;
	}

	/**
	 * @param profileLink the profileLink to set
	 */
	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}

	/**
	 * @return the authProvider
	 */
	public AuthProvider getAuthProvider() {
		return authProvider;
	}

}
