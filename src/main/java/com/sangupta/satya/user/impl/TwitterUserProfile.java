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
 * {@link UserProfile} implementation for Twitter.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class TwitterUserProfile extends BaseUserProfile {
	
	private long id;
	
	private String idStr;
	
	private String name;
	
	private String screenName;
	
	private String location;
	
	private String description;
	
	private String url;
	
	private boolean protectedUser; // TODO: the actual attribute is protected - need to fix this
	
	private long followersCount;
	
	private long friendsCount;
	
	private long listedCount;
	
	private String createdAt;
	
	private long favouritesCount;
	
	private long utcOffset;
	
	private String timeZone;
	
	private boolean geoEnabled;
	
	private boolean verified;
	
	private long statusesCount;
	
	private String lang;
	
	private boolean contributorsEnabled;
	
	private boolean isTranslator;
	
	private boolean isTranslationEnabled;
	
	private String profileBackgroundColor;
	
	private String profileBackgroundImageUrl;
	
	private String profileBackgroundImageUrlHttps;
	
	private String profileBackgroundTile;
	
	private String profileImageUrl;
	
	private String profileImageUrlHttps;
	
	private String profileLinkColor;
	
	private String profileSidebarBorderColor;
	
	private String profileSidebarFillColor;
	
	private String profileTextColor;
	
	private boolean profileUseBackgroundImage;
	
	private boolean defaultProfile;
	
	private boolean defaultProfileImage;
	
	private boolean following;
	
	private boolean followRequestSent;
	
	private boolean notifications;
	
	public TwitterUserProfile() {
		super(AuthProvider.Twitter);
	}
	
	@Override
	public int hashCode() {
		return (int)(this.id ^ (this.id >>> 32));
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(!(obj instanceof TwitterUserProfile)) {
			return false;
		}
		
		TwitterUserProfile other = (TwitterUserProfile) obj;
		return this.id == other.id;
	}

	@Override
	public String getUserID() {
		return this.idStr;
	}

	@Override
	public String getDisplayName() {
		return this.screenName;
	}

	@Override
	public String getProfileLink() {
		return "https://twitter.com/" + this.screenName;
	}

	@Override
	public String getProfileImageURL() {
		return this.profileImageUrlHttps;
	}
	
	// Usual accessors follow

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the idStr
	 */
	public String getIdStr() {
		return idStr;
	}

	/**
	 * @param idStr the idStr to set
	 */
	public void setIdStr(String idStr) {
		this.idStr = idStr;
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
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the protectedUser
	 */
	public boolean isProtectedUser() {
		return protectedUser;
	}

	/**
	 * @param protectedUser the protectedUser to set
	 */
	public void setProtectedUser(boolean protectedUser) {
		this.protectedUser = protectedUser;
	}

	/**
	 * @return the followersCount
	 */
	public long getFollowersCount() {
		return followersCount;
	}

	/**
	 * @param followersCount the followersCount to set
	 */
	public void setFollowersCount(long followersCount) {
		this.followersCount = followersCount;
	}

	/**
	 * @return the friendsCount
	 */
	public long getFriendsCount() {
		return friendsCount;
	}

	/**
	 * @param friendsCount the friendsCount to set
	 */
	public void setFriendsCount(long friendsCount) {
		this.friendsCount = friendsCount;
	}

	/**
	 * @return the listedCount
	 */
	public long getListedCount() {
		return listedCount;
	}

	/**
	 * @param listedCount the listedCount to set
	 */
	public void setListedCount(long listedCount) {
		this.listedCount = listedCount;
	}

	/**
	 * @return the createdAt
	 */
	public String getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the favouritesCount
	 */
	public long getFavouritesCount() {
		return favouritesCount;
	}

	/**
	 * @param favouritesCount the favouritesCount to set
	 */
	public void setFavouritesCount(long favouritesCount) {
		this.favouritesCount = favouritesCount;
	}

	/**
	 * @return the utcOffset
	 */
	public long getUtcOffset() {
		return utcOffset;
	}

	/**
	 * @param utcOffset the utcOffset to set
	 */
	public void setUtcOffset(long utcOffset) {
		this.utcOffset = utcOffset;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return the geoEnabled
	 */
	public boolean isGeoEnabled() {
		return geoEnabled;
	}

	/**
	 * @param geoEnabled the geoEnabled to set
	 */
	public void setGeoEnabled(boolean geoEnabled) {
		this.geoEnabled = geoEnabled;
	}

	/**
	 * @return the verified
	 */
	public boolean isVerified() {
		return verified;
	}

	/**
	 * @param verified the verified to set
	 */
	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	/**
	 * @return the statusesCount
	 */
	public long getStatusesCount() {
		return statusesCount;
	}

	/**
	 * @param statusesCount the statusesCount to set
	 */
	public void setStatusesCount(long statusesCount) {
		this.statusesCount = statusesCount;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the contributorsEnabled
	 */
	public boolean isContributorsEnabled() {
		return contributorsEnabled;
	}

	/**
	 * @param contributorsEnabled the contributorsEnabled to set
	 */
	public void setContributorsEnabled(boolean contributorsEnabled) {
		this.contributorsEnabled = contributorsEnabled;
	}

	/**
	 * @return the isTranslator
	 */
	public boolean isTranslator() {
		return isTranslator;
	}

	/**
	 * @param isTranslator the isTranslator to set
	 */
	public void setTranslator(boolean isTranslator) {
		this.isTranslator = isTranslator;
	}

	/**
	 * @return the isTranslationEnabled
	 */
	public boolean isTranslationEnabled() {
		return isTranslationEnabled;
	}

	/**
	 * @param isTranslationEnabled the isTranslationEnabled to set
	 */
	public void setTranslationEnabled(boolean isTranslationEnabled) {
		this.isTranslationEnabled = isTranslationEnabled;
	}

	/**
	 * @return the profileBackgroundColor
	 */
	public String getProfileBackgroundColor() {
		return profileBackgroundColor;
	}

	/**
	 * @param profileBackgroundColor the profileBackgroundColor to set
	 */
	public void setProfileBackgroundColor(String profileBackgroundColor) {
		this.profileBackgroundColor = profileBackgroundColor;
	}

	/**
	 * @return the profileBackgroundImageUrl
	 */
	public String getProfileBackgroundImageUrl() {
		return profileBackgroundImageUrl;
	}

	/**
	 * @param profileBackgroundImageUrl the profileBackgroundImageUrl to set
	 */
	public void setProfileBackgroundImageUrl(String profileBackgroundImageUrl) {
		this.profileBackgroundImageUrl = profileBackgroundImageUrl;
	}

	/**
	 * @return the profileBackgroundImageUrlHttps
	 */
	public String getProfileBackgroundImageUrlHttps() {
		return profileBackgroundImageUrlHttps;
	}

	/**
	 * @param profileBackgroundImageUrlHttps the profileBackgroundImageUrlHttps to set
	 */
	public void setProfileBackgroundImageUrlHttps(
			String profileBackgroundImageUrlHttps) {
		this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
	}

	/**
	 * @return the profileBackgroundTile
	 */
	public String getProfileBackgroundTile() {
		return profileBackgroundTile;
	}

	/**
	 * @param profileBackgroundTile the profileBackgroundTile to set
	 */
	public void setProfileBackgroundTile(String profileBackgroundTile) {
		this.profileBackgroundTile = profileBackgroundTile;
	}

	/**
	 * @return the profileImageUrl
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	/**
	 * @param profileImageUrl the profileImageUrl to set
	 */
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	/**
	 * @return the profileImageUrlHttps
	 */
	public String getProfileImageUrlHttps() {
		return profileImageUrlHttps;
	}

	/**
	 * @param profileImageUrlHttps the profileImageUrlHttps to set
	 */
	public void setProfileImageUrlHttps(String profileImageUrlHttps) {
		this.profileImageUrlHttps = profileImageUrlHttps;
	}

	/**
	 * @return the profileLinkColor
	 */
	public String getProfileLinkColor() {
		return profileLinkColor;
	}

	/**
	 * @param profileLinkColor the profileLinkColor to set
	 */
	public void setProfileLinkColor(String profileLinkColor) {
		this.profileLinkColor = profileLinkColor;
	}

	/**
	 * @return the profileSidebarBorderColor
	 */
	public String getProfileSidebarBorderColor() {
		return profileSidebarBorderColor;
	}

	/**
	 * @param profileSidebarBorderColor the profileSidebarBorderColor to set
	 */
	public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
		this.profileSidebarBorderColor = profileSidebarBorderColor;
	}

	/**
	 * @return the profileSidebarFillColor
	 */
	public String getProfileSidebarFillColor() {
		return profileSidebarFillColor;
	}

	/**
	 * @param profileSidebarFillColor the profileSidebarFillColor to set
	 */
	public void setProfileSidebarFillColor(String profileSidebarFillColor) {
		this.profileSidebarFillColor = profileSidebarFillColor;
	}

	/**
	 * @return the profileTextColor
	 */
	public String getProfileTextColor() {
		return profileTextColor;
	}

	/**
	 * @param profileTextColor the profileTextColor to set
	 */
	public void setProfileTextColor(String profileTextColor) {
		this.profileTextColor = profileTextColor;
	}

	/**
	 * @return the profileUseBackgroundImage
	 */
	public boolean isProfileUseBackgroundImage() {
		return profileUseBackgroundImage;
	}

	/**
	 * @param profileUseBackgroundImage the profileUseBackgroundImage to set
	 */
	public void setProfileUseBackgroundImage(boolean profileUseBackgroundImage) {
		this.profileUseBackgroundImage = profileUseBackgroundImage;
	}

	/**
	 * @return the defaultProfile
	 */
	public boolean isDefaultProfile() {
		return defaultProfile;
	}

	/**
	 * @param defaultProfile the defaultProfile to set
	 */
	public void setDefaultProfile(boolean defaultProfile) {
		this.defaultProfile = defaultProfile;
	}

	/**
	 * @return the defaultProfileImage
	 */
	public boolean isDefaultProfileImage() {
		return defaultProfileImage;
	}

	/**
	 * @param defaultProfileImage the defaultProfileImage to set
	 */
	public void setDefaultProfileImage(boolean defaultProfileImage) {
		this.defaultProfileImage = defaultProfileImage;
	}

	/**
	 * @return the following
	 */
	public boolean isFollowing() {
		return following;
	}

	/**
	 * @param following the following to set
	 */
	public void setFollowing(boolean following) {
		this.following = following;
	}

	/**
	 * @return the followRequestSent
	 */
	public boolean isFollowRequestSent() {
		return followRequestSent;
	}

	/**
	 * @param followRequestSent the followRequestSent to set
	 */
	public void setFollowRequestSent(boolean followRequestSent) {
		this.followRequestSent = followRequestSent;
	}

	/**
	 * @return the notifications
	 */
	public boolean isNotifications() {
		return notifications;
	}

	/**
	 * @param notifications the notifications to set
	 */
	public void setNotifications(boolean notifications) {
		this.notifications = notifications;
	}

}
