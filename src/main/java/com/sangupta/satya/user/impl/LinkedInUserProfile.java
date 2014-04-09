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
 * {@link UserProfile} implementation for LinkedIn.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class LinkedInUserProfile extends BaseUserProfile {
	
	private String id;
	
	private String firstName;
	
	private String headline;
	
	private String lastName;
	
	private String pictureUrl;
	
	private String publicProfileUrl;
	
	private String summary;
	
	private LinkedInSiteStandardProfile siteStandardProfileRequest;

	public LinkedInUserProfile() {
		super(AuthProvider.LinkedIn);
	}

	@Override
	public String getUserID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public String getProfileLink() {
		return this.publicProfileUrl;
	}

	@Override
	public String getProfileImageURL() {
		return this.pictureUrl;
	}
	
	// Usual accessors follow

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
	 * @return the headline
	 */
	public String getHeadline() {
		return headline;
	}

	/**
	 * @param headline the headline to set
	 */
	public void setHeadline(String headline) {
		this.headline = headline;
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
	
	// STATIC CLASS
	
	public static class LinkedInSiteStandardProfile {
		
		public String url;
		
	}

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
	 * @return the pictureUrl
	 */
	public String getPictureUrl() {
		return pictureUrl;
	}

	/**
	 * @param pictureUrl the pictureUrl to set
	 */
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	/**
	 * @return the publicProfileUrl
	 */
	public String getPublicProfileUrl() {
		return publicProfileUrl;
	}

	/**
	 * @param publicProfileUrl the publicProfileUrl to set
	 */
	public void setPublicProfileUrl(String publicProfileUrl) {
		this.publicProfileUrl = publicProfileUrl;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the siteStandardProfileRequest
	 */
	public LinkedInSiteStandardProfile getSiteStandardProfileRequest() {
		return siteStandardProfileRequest;
	}

	/**
	 * @param siteStandardProfileRequest the siteStandardProfileRequest to set
	 */
	public void setSiteStandardProfileRequest(
			LinkedInSiteStandardProfile siteStandardProfileRequest) {
		this.siteStandardProfileRequest = siteStandardProfileRequest;
	}

}
