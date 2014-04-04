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

package com.sangupta.satya.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.http.WebRequestMethod;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.extractor.UrlParamExtractor;
import com.sangupta.jerry.oauth.service.OAuth1ServiceImpl;
import com.sangupta.jerry.oauth.service.OAuth2ServiceImpl;
import com.sangupta.jerry.oauth.service.OAuthService;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.user.BaseAuthenticatedUser;

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
	
	/**
	 * The scopes to use when requesting permissions
	 */
	protected final String scope;
	
	/**
	 * Constructor that takes in the final {@link OAuthService} to use and the
	 * scopes that we need to interact with.
	 * 
	 * @param service
	 * @param scope
	 */
	public BaseAuthClient(OAuthService service, String scope) {
		this.service = service;
		this.scope = scope;
	}
	
	/**
	 * @see AuthClient#getLoginRedirectURL(String)
	 */
	@Override
	public String getLoginRedirectURL(String successUrl) {
		return service.getLoginURL(successUrl, scope);
	}
	
	protected abstract String getProviderName();
	
	@Override
	public AuthenticatedUser verifyUser(HttpServletRequest request, final String redirectURL) {
		// extract the code from the request parameter
		String errorCode = request.getParameter("error");
		if(AssertUtils.isNotEmpty(errorCode)) {
			// TODO: make checked exceptions so that user's know what's happening
			return null;
		}
		
		// find the actual code
		String code = request.getParameter("code");
		
		return this.verifyUser(code, redirectURL);
	}
	
	/**
	 * 
	 * @return
	 */
	protected abstract TokenExtractor getTokenExtractor();
	
	@Override
	public AuthenticatedUser verifyUser(String verifier, String redirectURL) {
		if(this.service instanceof OAuth2ServiceImpl) {
			return verifyUserOAuth2(verifier, redirectURL);
		}
		
		if(this.service instanceof OAuth1ServiceImpl) {
			return verifyUserOAuth1(verifier, redirectURL);
		}
		
		throw new IllegalStateException("Don't know how to handle the verification of user");
	}
	
	protected AuthenticatedUser verifyUserOAuth1(String verifier, String redirectURL) {
		int index = redirectURL.indexOf("?");
		final String token = UrlParamExtractor.INSTANCE.extractTokens(redirectURL.substring(index + 1)).get("oauth_token");
		
		if(AssertUtils.isEmpty(token) || AssertUtils.isEmpty(verifier)) {
			throw new IllegalArgumentException("The request does not appear to be a valid " + getProviderName() + " request");
		}
		
		// obtain the authorization code
		String response = ((OAuth1ServiceImpl) this.service).getAuthorizationResponse(token, verifier, redirectURL);
		if(AssertUtils.isEmpty(response)) {
			return null;
		}
		
		Map<String, String> map = new UrlParamExtractor().extractTokens(response);
    	return new BaseAuthenticatedUser(map.get("oauth_token"), map.get("oauth_token_secret"), "", 3600, this);
	}
	
	/**
	 * 
	 * @param verifier
	 * @param redirectURL
	 * @return
	 */
	protected AuthenticatedUser verifyUserOAuth2(String verifier, String redirectURL) {
		if(AssertUtils.isEmpty(verifier)) {
			throw new IllegalArgumentException("The request does not appear to be a valid " + getProviderName() + " request");
		}
		
		System.out.println("Using " + getProviderName() + " verification code: " + verifier);
		// obtain the authorization code
		String response = ((OAuth2ServiceImpl) this.service).getAuthorizationResponse(verifier, null, redirectURL);
		if(AssertUtils.isEmpty(response)) {
			return null;
		}
		
		Map<String, String> map = getTokenExtractor().extractTokens(response);
    	return new BaseAuthenticatedUser(map.get("access_token"), "", map.get("refresh_token"), 3600, this);
	}

	/**
	 * @see AuthClient#signOut()
	 */
	@Override
	public boolean signOut() {
		return false;
	}

	/**
	 * @see AuthClient#getUsingJson(KeySecretPair, String, Class)
	 */
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
	 * @see AuthClient#signRequest(KeySecretPair, WebRequest)
	 */
	@Override
	public void signRequest(KeySecretPair accessPair, WebRequest request) {
		this.service.signRequest(request, accessPair);
	}
}
