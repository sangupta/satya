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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.sangupta.jerry.http.WebInvoker;
import com.sangupta.jerry.http.WebRequest;
import com.sangupta.jerry.http.WebRequestMethod;
import com.sangupta.jerry.http.WebResponse;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.domain.TokenAndUrl;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.service.OAuth1ServiceImpl;
import com.sangupta.jerry.oauth.service.OAuth2ServiceImpl;
import com.sangupta.jerry.oauth.service.OAuthService;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.user.BaseAuthenticatedUser;

/**
 * 
 * @author sangupta
 *
 */
public abstract class BaseAuthClient implements AuthClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseAuthClient.class);
	
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
	public TokenAndUrl getLoginRedirectURL(String successUrl) {
		return service.getLoginURL(successUrl, scope);
	}
	
	/**
	 * Indicate the {@link AuthProvider} that we handle.
	 * 
	 * @return
	 */
	protected abstract AuthProvider getAuthProvider();
	
	/**
	 * Verify the incoming user.
	 * 
	 */
	@Override
	public AuthenticatedUser verifyUser(HttpServletRequest request, final TokenAndUrl tokenAndUrl) {
		// extract the code from the request parameter
		String errorCode = request.getParameter("error");
		if(AssertUtils.isNotEmpty(errorCode)) {
			// TODO: make checked exceptions so that user's know what's happening
			return null;
		}
		
		// find the actual code
		String code = request.getParameter("code");
		
		return this.verifyUser(code, tokenAndUrl);
	}
	
	/**
	 * 
	 * @return
	 */
	protected abstract TokenExtractor getTokenExtractor();
	
	@Override
	public AuthenticatedUser verifyUser(String verifier, TokenAndUrl tokenAndUrl) {
		if(this.service instanceof OAuth2ServiceImpl) {
			return verifyUserOAuth2(tokenAndUrl, verifier);
		}
		
		if(this.service instanceof OAuth1ServiceImpl) {
			return verifyUserOAuth1(tokenAndUrl, verifier);
		}
		
		throw new IllegalStateException("Don't know how to handle the verification of user");
	}
	
	protected AuthenticatedUser verifyUserOAuth1(TokenAndUrl tokenAndUrl, String verifier) {
		if(AssertUtils.isEmpty(verifier)) {
			throw new IllegalArgumentException("The request does not appear to be a valid " + getAuthProvider() + " request");
		}
		
		// obtain the authorization code
		String response = this.service.getAuthorizationResponse(tokenAndUrl, verifier);
		if(AssertUtils.isEmpty(response)) {
			return null;
		}
		
		Map<String, String> map = getTokenExtractor().extractTokens(response);
    	return new BaseAuthenticatedUser(map.get("oauth_token"), map.get("oauth_token_secret"), "", 3600, this);
	}
	
	/**
	 * 
	 * @param verifier
	 * @param redirectURL
	 * @return
	 */
	protected AuthenticatedUser verifyUserOAuth2(TokenAndUrl tokenAndUrl, String verifier) {
		if(AssertUtils.isEmpty(verifier)) {
			throw new IllegalArgumentException("The request does not appear to be a valid " + getAuthProvider() + " request");
		}
		
		System.out.println("Using " + getAuthProvider() + " verification code: " + verifier);
		// obtain the authorization code
		String response = this.service.getAuthorizationResponse(tokenAndUrl, verifier);
		if(AssertUtils.isEmpty(response)) {
			return null;
		}
		
		Map<String, String> map = getTokenExtractor().extractTokens(response);
    	return new BaseAuthenticatedUser(map.get("access_token"), "", map.get("refresh_token"), 3600, this);
	}

	/**
	 * The field naming policy to be used when parsing responses from JSON.
	 * 
	 * @return
	 */
	protected FieldNamingPolicy getFieldNamingPolicy() {
		return FieldNamingPolicy.IDENTITY;
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
		url = this.service.signRequestUrl(url, accessPair);
		LOGGER.debug("Hitting signed URL: {}", url);
		
		WebRequest request = WebInvoker.getWebRequest(url, WebRequestMethod.GET);
		this.service.signRequest(request, accessPair);
		
		WebResponse response = WebInvoker.executeSilently(request);
		if(response == null) {
			LOGGER.error("Null response from url: {}", url);
			return null;
		}
		
		if(!response.isSuccess()) {
			LOGGER.error("Invalid response from url: {} with response: {}", url, response.trace());
			LOGGER.error(response.getContent());
			return null;
		}
		
		String content = response.getContent();
		LOGGER.debug("Webresponse: " + content);
		return GsonUtils.getGson(getFieldNamingPolicy()).fromJson(content, clazz);
	}
	
	/**
	 * @see AuthClient#signRequest(KeySecretPair, WebRequest)
	 */
	@Override
	public void signRequest(KeySecretPair accessPair, WebRequest request) {
		this.service.signRequest(request, accessPair);
	}
}
