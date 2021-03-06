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
import com.sangupta.jerry.oauth.extractor.UrlParamTokenExtractor;
import com.sangupta.jerry.oauth.service.impl.YahooOAuthServiceImpl;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.AuthClient;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.BaseAuthenticatedUser;
import com.sangupta.satya.user.impl.YahooUserProfile;

/**
 * {@link AuthClient} for http://yahoo.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class YahooAuthClient extends BaseAuthClient {
	
	public YahooAuthClient(KeySecretPair pair, String... scopes) {
		super(new YahooOAuthServiceImpl(pair), scopes);
	}

	@Override
	protected AuthProvider getAuthProvider() {
		return AuthProvider.Yahoo;
	}

	@Override
	protected TokenExtractor getTokenExtractor() {
		return UrlParamTokenExtractor.INSTANCE;
	}
	
	@Override
	protected AuthenticatedUser createNewAuthenticatedUser(Map<String, String> rawParameters) {
		return new BaseAuthenticatedUser(this, rawParameters) {
			
			@Override
			public String getUserProfileURL() {
				String userID = this.rawParameters.get("xoauth_yahoo_guid");
				return "https://social.yahooapis.com/v1/user/" + userID + "/profile?format=json";
			}
			
			@Override
			public Class<? extends UserProfile> getUserProfileClass() {
				return YahooUserProfile.class;
			}
			
		};
	}

}
