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

import com.google.gson.FieldNamingPolicy;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.extractor.UrlParamTokenExtractor;
import com.sangupta.jerry.oauth.service.impl.GithubOAuthServiceImpl;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.AuthClient;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.BaseAuthenticatedUser;
import com.sangupta.satya.user.impl.GithubUserProfile;

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
	public GithubAuthClient(KeySecretPair keySecretPair, String... scopes) {
		super(new GithubOAuthServiceImpl(keySecretPair), scopes);
	}

	@Override
	protected AuthProvider getAuthProvider() {
		return AuthProvider.Github;
	}

	@Override
	protected TokenExtractor getTokenExtractor() {
		return UrlParamTokenExtractor.INSTANCE;
	}
	
	@Override
	protected FieldNamingPolicy getFieldNamingPolicy() {
		return FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
	}
	
	@Override
	protected AuthenticatedUser createNewAuthenticatedUser(Map<String, String> rawParameters) {
		return new BaseAuthenticatedUser(this, rawParameters) {
			
			@Override
			public String getUserProfileURL() {
				return "https://api.github.com/user";
			}
			
			@Override
			public Class<? extends UserProfile> getUserProfileClass() {
				return GithubUserProfile.class;
			}
		};
	}

}
