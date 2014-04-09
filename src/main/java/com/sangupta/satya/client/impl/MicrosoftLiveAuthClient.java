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

import com.google.gson.FieldNamingPolicy;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.JSONTokenExtractor;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.scope.MicrosoftLiveScopes;
import com.sangupta.jerry.oauth.service.impl.MicrosoftLiveOAuthServiceImpl;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.AuthClient;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.impl.MicrosoftLiveUserProfile;

/**
 * {@link AuthClient} for http://live.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class MicrosoftLiveAuthClient extends BaseAuthClient {
	
	public MicrosoftLiveAuthClient(KeySecretPair pair) {
		super(new MicrosoftLiveOAuthServiceImpl(pair), MicrosoftLiveScopes.READ_USER_PROFILE);
	}

	@Override
	protected AuthProvider getAuthProvider() {
		return AuthProvider.MicrosoftLive;
	}

	@Override
	protected TokenExtractor getTokenExtractor() {
		return JSONTokenExtractor.INSTANCE;
	}
	
	@Override
	protected FieldNamingPolicy getFieldNamingPolicy() {
		return FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		String url = "https://apis.live.net/v5.0/me";
		return this.getUsingJson(accessPair, url, MicrosoftLiveUserProfile.class);
	}

}
