/**
 *
 * satya - social authentication framework
 * Copyright (c) 2014, Sandeep Gupta
 * 
 * http://www.sangupta/projects/satya
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

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.scope.FacebookScopes;
import com.sangupta.jerry.oauth.service.impl.FacebookOAuthServiceImpl;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.UserProfile;

/**
 * 
 * @author sangupta
 *
 */
public class FacebookAuthClient extends BaseAuthClient {

	/**
	 * 
	 * @param keySecretPair
	 */
	public FacebookAuthClient(KeySecretPair keySecretPair) {
		super(new FacebookOAuthServiceImpl(keySecretPair), FacebookScopes.EMAIL);
	}

	@Override
	public AuthenticatedUser verifyUser(HttpServletRequest request, String redirectURL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		// TODO Auto-generated method stub
		return null;
	}

}
