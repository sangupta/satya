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

package com.sangupta.satya.client;

import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.http.WebRequestMethod;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.service.OAuthService;
import com.sangupta.jerry.util.GsonUtils;

/**
 * 
 * @author sangupta
 *
 */
public abstract class BaseAuthClient implements AuthClient {
	
	/**
	 * The {@link OAuthService} to use
	 * 
	 */
	protected final OAuthService service;
	
	protected final String scope;
	
	public BaseAuthClient(OAuthService service, String scope) {
		this.service = service;
		this.scope = scope;
	}
	
	@Override
	public String getLoginRedirectURL(String successUrl) {
		return service.getLoginURL(successUrl, scope);
	}
	
	@Override
	public boolean signOut() {
		return false;
	}

	@Override
	public <T> T getUsingJson(KeySecretPair accessPair, String url, Class<T> clazz) {
		WebRequest request = WebInvoker.getWebRequest(url, WebRequestMethod.GET);
		this.service.signRequest(request, accessPair);
		WebResponse response = WebInvoker.executeSilently(request);
		if(response != null) {
			System.out.println("Response: " + response.trace());
			System.out.println(response.getContent());
		}
		
		if(!response.isSuccess()) {
			return null;
		}
		
		String content = response.getContent();
		System.out.println("Webresponse: " + content);
		return GsonUtils.getGson().fromJson(content, clazz);
	}
	
	/**
	 * 
	 */
	@Override
	public void signRequest(KeySecretPair accessPair, WebRequest request) {
		this.service.signRequest(request, accessPair);
	}
}
