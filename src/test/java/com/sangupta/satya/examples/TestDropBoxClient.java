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

package com.sangupta.satya.examples;

import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.satya.client.impl.DropBoxAuthClient;

/**
 * Example to test {@link DropBoxAuthClient}
 * 
 * @author sangupta
 * @since 1.0
 */
public class TestDropBoxClient {

	public static void main(String[] args) {
		WebResponse response = WebInvoker.getResponse("http://www.google.com");
		System.out.println(response.getContent());
		
		System.out.println(GsonUtils.getGson().toJson(response));
		
//		String redirectURL = "http://localhost/satya/oauth/drpobox";
//		
//		AuthConfig config = new AuthConfig();
//		
//		KeySecretPair dropBox = new KeySecretPair(ApiKeys.DropBox, ApiKeys.DropBoxSecret);
//		config.addConfig(AuthProvider.DropBox, dropBox);
//		
//		AuthManager.loadConfig(config);
//		
//		String url = AuthManager.getAuthRedirectURL(AuthProvider.DropBox, AuthPermissions.DEFAULT, redirectURL);
//		System.out.println(url);
//		
//		String code = ConsoleUtils.readLine("code: ", true);
//		
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		request.addParameter("code", code);
//		AuthenticatedUser user = AuthManager.authenticateUser(request, redirectURL);
//		System.out.println("User: " + user);
//		
//		if(user != null) {
//			UserProfile profile = user.getUserProfile();
//			System.out.println(profile);
//		}
	}
	
}
