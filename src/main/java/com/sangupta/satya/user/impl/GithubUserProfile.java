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
 * {@link UserProfile} implementation for Github.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class GithubUserProfile extends BaseUserProfile {
	
	private String login;
	
	private long id;
	
	private String avatarUrl;
	
	private String gravatarId;
	
	private String url;
	
	private String htmlUrl;
	
	private String followersUrl;
	
	private String followingUrl;
	
	private String gistsUrl;
	
	private String starredUrl;
	
	private String subscriptionsUrl;
	
	private String organizationsUrl;
	
	private String reposUrl;
	
	private String eventsUrl;
	
	private String receivedEventsUrl;
	
	private String type;
	
	private boolean siteAdmin;
	
	private String name;
	
	private String company;
	
	private String blog;
	
	private String location;
	
	private boolean hirable;
	
	private String bio;
	
	private long publicRepos;
	
	private long publicGists;
	
	private long followers;
	
	private long following;
	
	private String createdAt;
	
	private String updatedAt;
	
	private long totalPrivateRepos;
	
	private long ownedPrivateRepos;
	
	private long privateGists;
	
	private long diskUsage;
	
	private long collaborators;
	
	private GithubPlan plan;

	/**
	 * Default constructor
	 */
	public GithubUserProfile() {
		super(AuthProvider.Github);
	}
	
	@Override
	public String getUserID() {
		return String.valueOf(this.id);
	}

	@Override
	public String getDisplayName() {
		return this.name;
	}

	@Override
	public String getProfileLink() {
		return this.htmlUrl;
	}

	@Override
	public String getProfileImageURL() {
		return this.avatarUrl;
	}

	// Usual accessors follow

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

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
	 * @return the avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * @param avatarUrl the avatarUrl to set
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	/**
	 * @return the gravatarId
	 */
	public String getGravatarId() {
		return gravatarId;
	}

	/**
	 * @param gravatarId the gravatarId to set
	 */
	public void setGravatarId(String gravatarId) {
		this.gravatarId = gravatarId;
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
	 * @return the htmlUrl
	 */
	public String getHtmlUrl() {
		return htmlUrl;
	}

	/**
	 * @param htmlUrl the htmlUrl to set
	 */
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	/**
	 * @return the followersUrl
	 */
	public String getFollowersUrl() {
		return followersUrl;
	}

	/**
	 * @param followersUrl the followersUrl to set
	 */
	public void setFollowersUrl(String followersUrl) {
		this.followersUrl = followersUrl;
	}

	/**
	 * @return the followingUrl
	 */
	public String getFollowingUrl() {
		return followingUrl;
	}

	/**
	 * @param followingUrl the followingUrl to set
	 */
	public void setFollowingUrl(String followingUrl) {
		this.followingUrl = followingUrl;
	}

	/**
	 * @return the gistsUrl
	 */
	public String getGistsUrl() {
		return gistsUrl;
	}

	/**
	 * @param gistsUrl the gistsUrl to set
	 */
	public void setGistsUrl(String gistsUrl) {
		this.gistsUrl = gistsUrl;
	}

	/**
	 * @return the starredUrl
	 */
	public String getStarredUrl() {
		return starredUrl;
	}

	/**
	 * @param starredUrl the starredUrl to set
	 */
	public void setStarredUrl(String starredUrl) {
		this.starredUrl = starredUrl;
	}

	/**
	 * @return the subscriptionsUrl
	 */
	public String getSubscriptionsUrl() {
		return subscriptionsUrl;
	}

	/**
	 * @param subscriptionsUrl the subscriptionsUrl to set
	 */
	public void setSubscriptionsUrl(String subscriptionsUrl) {
		this.subscriptionsUrl = subscriptionsUrl;
	}

	/**
	 * @return the organizationsUrl
	 */
	public String getOrganizationsUrl() {
		return organizationsUrl;
	}

	/**
	 * @param organizationsUrl the organizationsUrl to set
	 */
	public void setOrganizationsUrl(String organizationsUrl) {
		this.organizationsUrl = organizationsUrl;
	}

	/**
	 * @return the reposUrl
	 */
	public String getReposUrl() {
		return reposUrl;
	}

	/**
	 * @param reposUrl the reposUrl to set
	 */
	public void setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
	}

	/**
	 * @return the eventsUrl
	 */
	public String getEventsUrl() {
		return eventsUrl;
	}

	/**
	 * @param eventsUrl the eventsUrl to set
	 */
	public void setEventsUrl(String eventsUrl) {
		this.eventsUrl = eventsUrl;
	}

	/**
	 * @return the receivedEventsUrl
	 */
	public String getReceivedEventsUrl() {
		return receivedEventsUrl;
	}

	/**
	 * @param receivedEventsUrl the receivedEventsUrl to set
	 */
	public void setReceivedEventsUrl(String receivedEventsUrl) {
		this.receivedEventsUrl = receivedEventsUrl;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the siteAdmin
	 */
	public boolean isSiteAdmin() {
		return siteAdmin;
	}

	/**
	 * @param siteAdmin the siteAdmin to set
	 */
	public void setSiteAdmin(boolean siteAdmin) {
		this.siteAdmin = siteAdmin;
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
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the blog
	 */
	public String getBlog() {
		return blog;
	}

	/**
	 * @param blog the blog to set
	 */
	public void setBlog(String blog) {
		this.blog = blog;
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
	 * @return the hirable
	 */
	public boolean isHirable() {
		return hirable;
	}

	/**
	 * @param hirable the hirable to set
	 */
	public void setHirable(boolean hirable) {
		this.hirable = hirable;
	}

	/**
	 * @return the bio
	 */
	public String getBio() {
		return bio;
	}

	/**
	 * @param bio the bio to set
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}

	/**
	 * @return the publicRepos
	 */
	public long getPublicRepos() {
		return publicRepos;
	}

	/**
	 * @param publicRepos the publicRepos to set
	 */
	public void setPublicRepos(long publicRepos) {
		this.publicRepos = publicRepos;
	}

	/**
	 * @return the publicGists
	 */
	public long getPublicGists() {
		return publicGists;
	}

	/**
	 * @param publicGists the publicGists to set
	 */
	public void setPublicGists(long publicGists) {
		this.publicGists = publicGists;
	}

	/**
	 * @return the followers
	 */
	public long getFollowers() {
		return followers;
	}

	/**
	 * @param followers the followers to set
	 */
	public void setFollowers(long followers) {
		this.followers = followers;
	}

	/**
	 * @return the following
	 */
	public long getFollowing() {
		return following;
	}

	/**
	 * @param following the following to set
	 */
	public void setFollowing(long following) {
		this.following = following;
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
	 * @return the updatedAt
	 */
	public String getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the totalPrivateRepos
	 */
	public long getTotalPrivateRepos() {
		return totalPrivateRepos;
	}

	/**
	 * @param totalPrivateRepos the totalPrivateRepos to set
	 */
	public void setTotalPrivateRepos(long totalPrivateRepos) {
		this.totalPrivateRepos = totalPrivateRepos;
	}

	/**
	 * @return the ownedPrivateRepos
	 */
	public long getOwnedPrivateRepos() {
		return ownedPrivateRepos;
	}

	/**
	 * @param ownedPrivateRepos the ownedPrivateRepos to set
	 */
	public void setOwnedPrivateRepos(long ownedPrivateRepos) {
		this.ownedPrivateRepos = ownedPrivateRepos;
	}

	/**
	 * @return the privateGists
	 */
	public long getPrivateGists() {
		return privateGists;
	}

	/**
	 * @param privateGists the privateGists to set
	 */
	public void setPrivateGists(long privateGists) {
		this.privateGists = privateGists;
	}

	/**
	 * @return the diskUsage
	 */
	public long getDiskUsage() {
		return diskUsage;
	}

	/**
	 * @param diskUsage the diskUsage to set
	 */
	public void setDiskUsage(long diskUsage) {
		this.diskUsage = diskUsage;
	}

	/**
	 * @return the collaborators
	 */
	public long getCollaborators() {
		return collaborators;
	}

	/**
	 * @param collaborators the collaborators to set
	 */
	public void setCollaborators(long collaborators) {
		this.collaborators = collaborators;
	}

	/**
	 * @return the plan
	 */
	public GithubPlan getPlan() {
		return plan;
	}

	/**
	 * @param plan the plan to set
	 */
	public void setPlan(GithubPlan plan) {
		this.plan = plan;
	}

	// EMBEDDED STATIC CLASS

	public static class GithubPlan {
		
		private String name;
		
		private long space;
		
		private long collaborators;
		
		private long privateRepos;
		
		// Usual accessors follow

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
		 * @return the space
		 */
		public long getSpace() {
			return space;
		}

		/**
		 * @param space the space to set
		 */
		public void setSpace(long space) {
			this.space = space;
		}

		/**
		 * @return the collaborators
		 */
		public long getCollaborators() {
			return collaborators;
		}

		/**
		 * @param collaborators the collaborators to set
		 */
		public void setCollaborators(long collaborators) {
			this.collaborators = collaborators;
		}

		/**
		 * @return the privateRepos
		 */
		public long getPrivateRepos() {
			return privateRepos;
		}

		/**
		 * @param privateRepos the privateRepos to set
		 */
		public void setPrivateRepos(long privateRepos) {
			this.privateRepos = privateRepos;
		}

	}

}
