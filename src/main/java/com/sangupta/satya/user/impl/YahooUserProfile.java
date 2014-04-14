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
 * {@link UserProfile} implementation for Yahoo.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class YahooUserProfile extends BaseUserProfile {
	
	private YahooProfile profile;

	public YahooUserProfile() {
		super(AuthProvider.Yahoo);
	}

	@Override
	public String getUserID() {
		return this.profile.guid;
	}

	@Override
	public String getDisplayName() {
		return this.profile.nickname;
	}

	@Override
	public String getProfileLink() {
		return this.profile.profileUrl;
	}

	@Override
	public String getProfileImageURL() {
		if(this.profile.image == null) {
			return null;
		}
		
		return this.profile.image.imageUrl;
	}
	
	/**
	 * @return the profile
	 */
	public YahooProfile getProfile() {
		return profile;
	}
	
	// STATIC classes follow
	
	public static class YahooProfile {
		
		public String uri;
		
		public String guid;
		
		public boolean bdRestricted;
		
		public String created;
		
		public YahooProfileEmail[] emails;
		
		public YahooProfileImage image;
		
		public String memberSince;
		
		public String nickname;
		
		public String profileUrl;
		
		public boolean isConnected;
	}
	
	public static class YahooProfileEmail {
		
		public String handle;
		
		public long id;
		
		public boolean primary;
		
		public String type;
		
	}
	
	public static class YahooProfileImage {
		
		public int height;
		
		public String imageUrl;
		
		public String size;
		
		public int width;
		
	}

}
