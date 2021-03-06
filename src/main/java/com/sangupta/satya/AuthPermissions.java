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

package com.sangupta.satya;

import com.sangupta.jerry.oauth.scope.FacebookScopes;
import com.sangupta.jerry.oauth.scope.GithubScopes;
import com.sangupta.jerry.oauth.scope.GoogleScopes;
import com.sangupta.jerry.oauth.scope.LinkedInScopes;
import com.sangupta.jerry.oauth.scope.MicrosoftLiveScopes;
import com.sangupta.jerry.util.StringUtils;

/**
 * Various permissions that a user may seek while authenticating with
 * the social network.
 * 
 * @author sangupta
 * @since 1.0
 */
public enum AuthPermissions {
	
	BASIC_PROFILE,
	
	PUBLISH;

	public String[] getScopes(AuthProvider provider) {
		switch(this) {
			case BASIC_PROFILE:
				return basicProfileScopes(provider);
				
			case PUBLISH:
				return publishScopes(provider);
		}
		
		return null;
	}

	/**
	 * @param provider
	 * @return
	 */
	private String[] basicProfileScopes(AuthProvider provider) {
		switch(provider) {
			case DropBox:
				return StringUtils.EMPTY_STRING_LIST;
				
			case Facebook:
				return new String[] { FacebookScopes.EMAIL, FacebookScopes.BASIC_INFO };
				
			case Github:
				return new String[] { GithubScopes.PUBLIC_INFO };
				
			case Google:
				return new String[] { GoogleScopes.EMAIL, GoogleScopes.PROFILE };
				
			case LinkedIn:
				return new String[] { LinkedInScopes.FULL_PROFILE };
				
			case MicrosoftLive:
				return new String[] { MicrosoftLiveScopes.READ_USER_PROFILE };
				
			case Twitter:
				return StringUtils.EMPTY_STRING_LIST;
				
			case Yahoo:
				return StringUtils.EMPTY_STRING_LIST;
		}
		
		return StringUtils.EMPTY_STRING_LIST;
	}
	
	/**
	 * @param provider
	 * @return
	 */
	private String[] publishScopes(AuthProvider provider) {
		switch(provider) {
			case DropBox:
				return StringUtils.EMPTY_STRING_LIST;
				
			case Facebook:
				return new String[] { FacebookScopes.PUBLISH_ACTIONS };
				
			case Github:
				return new String[] { GithubScopes.PUBLIC_INFO, GithubScopes.GIST, GithubScopes.NOTIFICATIONS, GithubScopes.WRITE_ORG, GithubScopes.WRITE_REPO_HOOK, GithubScopes.USER };
				
			case Google:
				return new String[] { GoogleScopes.EMAIL, GoogleScopes.PROFILE };
				
			case LinkedIn:
				return new String[] { LinkedInScopes.FULL_PROFILE, LinkedInScopes.NETWORK_UPDATES, LinkedInScopes.MESSAGES, LinkedInScopes.GROUPS };
				
			case MicrosoftLive:
				return new String[] { MicrosoftLiveScopes.READ_USER_PROFILE, MicrosoftLiveScopes.OFFLINE_ACCESS };
				
			case Twitter:
				return StringUtils.EMPTY_STRING_LIST;
				
			case Yahoo:
				return StringUtils.EMPTY_STRING_LIST;
		}
		
		return StringUtils.EMPTY_STRING_LIST;
	}

}
