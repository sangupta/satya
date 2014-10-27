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

package com.sangupta.satya.user;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.util.StringUtils;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.impl.TwitterAuthClient;
import com.sangupta.satya.user.impl.TwitterUserProfile;

/**
 * Create a validated user instance from pre-stored credential tokens
 * obtained as part of any previous authentication call.
 * 
 * @author sangupta
 *
 */
public class PreAuthenticatedUser extends BaseAuthenticatedUser {
	
	public PreAuthenticatedUser(AuthProvider provider, String apiKey, String apiSecret, String userToken, String userSecret) {
		super(new TwitterAuthClient(new KeySecretPair(apiKey, apiSecret), StringUtils.EMPTY_STRING_LIST), new KeySecretPair(userToken, userSecret));
	}

	@Override
	public String getUserProfileURL() {
		return "https://api.twitter.com/1.1/account/verify_credentials.json";
	}

	@Override
	public Class<? extends UserProfile> getUserProfileClass() {
		return TwitterUserProfile.class;
	}

}
