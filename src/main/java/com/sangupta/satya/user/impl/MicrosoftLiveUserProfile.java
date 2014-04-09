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

package com.sangupta.satya.user.impl;

import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.user.BaseUserProfile;

/**
 * {@link UserProfile} implementation for Live.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class MicrosoftLiveUserProfile extends BaseUserProfile {
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String name;
	
	private String gender;
	
	private String locale;

	public MicrosoftLiveUserProfile() {
		super(AuthProvider.MicrosoftLive);
	}

	@Override
	public String getUserID() {
		return this.id;
	}

	@Override
	public String getDisplayName() {
		return this.name;
	}

	@Override
	public String getProfileLink() {
		return null;
	}

	@Override
	public String getProfileImageURL() {
		return null;
	}
	
	// Usual accessors follow

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

}
