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
 * {@link UserProfile} implementation for DropBox.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class DropBoxUserProfile extends BaseUserProfile {
	
	private String referralLink;
	
	private String displayName;
	
	private long uid;
	
	private String country;

	public DropBoxUserProfile() {
		super(AuthProvider.DropBox);
	}

	@Override
	public String getUserID() {
		return String.valueOf(this.uid);
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
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
	 * @return the referralLink
	 */
	public String getReferralLink() {
		return referralLink;
	}

	/**
	 * @param referralLink the referralLink to set
	 */
	public void setReferralLink(String referralLink) {
		this.referralLink = referralLink;
	}

	/**
	 * @return the uid
	 */
	public long getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(long uid) {
		this.uid = uid;
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
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
