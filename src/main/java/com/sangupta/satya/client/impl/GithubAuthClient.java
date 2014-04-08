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

package com.sangupta.satya.client.impl;

import java.util.Map;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.extractor.UrlParamExtractor;
import com.sangupta.jerry.oauth.scope.GithubScopes;
import com.sangupta.jerry.oauth.service.impl.GithubOAuthServiceImpl;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.client.AuthClient;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.UserProfile;

/**
 * {@link AuthClient} for http://github.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class GithubAuthClient extends BaseAuthClient {

	/**
	 * 
	 * @param keySecretPair
	 */
	public GithubAuthClient(KeySecretPair keySecretPair) {
		super(new GithubOAuthServiceImpl(keySecretPair), GithubScopes.PUBLIC_INFO);
	}

	@Override
	protected String getProviderName() {
		return "Github";
	}

	@Override
	protected TokenExtractor getTokenExtractor() {
		return UrlParamExtractor.INSTANCE;
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		String url = "https://api.github.com/user";
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = this.getUsingJson(accessPair, url, Map.class);
		if(map == null) {
			return null;
		}
		
		UserProfile profile = new UserProfile(AuthProvider.Github, map.get("id"));
		
		profile.setFullName(map.get("name"));
		profile.setProfileImageURL(map.get("avatar_url"));
		profile.setEmail(map.get("email"));
		profile.setProfileLink(map.get("html_url"));
		
		return profile;
	}

}