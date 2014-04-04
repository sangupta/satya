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

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.extractor.UrlParamExtractor;
import com.sangupta.jerry.oauth.service.impl.TwitterOAuthServiceImpl;
import com.sangupta.satya.client.AuthClient;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.UserProfile;

/**
 * {@link AuthClient} for http://twitter.com
 * 
 * @author sangupta
 * @since 1.0
 */
public class TwitterAuthClient extends BaseAuthClient {
	
	@Override
	protected String getProviderName() {
		return "Twitter";
	}

	@Override
	protected TokenExtractor getTokenExtractor() {
		return UrlParamExtractor.INSTANCE;
	}

	public TwitterAuthClient(KeySecretPair pair) {
		super(new TwitterOAuthServiceImpl(pair), "");
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		return null;
	}

}
