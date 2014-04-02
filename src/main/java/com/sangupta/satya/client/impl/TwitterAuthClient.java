package com.sangupta.satya.client.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.extractor.UrlParamExtractor;
import com.sangupta.jerry.oauth.service.OAuth1ServiceImpl;
import com.sangupta.jerry.oauth.service.impl.TwitterOAuthServiceImpl;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.BaseAuthenticatedUser;
import com.sangupta.satya.user.UserProfile;

public class TwitterAuthClient extends BaseAuthClient {
	
	@Override
	protected String getProviderName() {
		return "Twitter";
	}

	@Override
	protected TokenExtractor getTokenExtractor() {
		return new UrlParamExtractor();
	}

	public TwitterAuthClient(KeySecretPair pair) {
		super(new TwitterOAuthServiceImpl(pair), "");
	}

	@Override
	public AuthenticatedUser verifyUser(HttpServletRequest request,	String redirectURL) {
		// read the OAuth token and secret from the request
		final String token = request.getParameter("oauth_token");
		final String verifier = request.getParameter("oauth_verifier");
		
		if(AssertUtils.isEmpty(token) || AssertUtils.isEmpty(verifier)) {
			throw new IllegalArgumentException("The request does not appear to be a valid Twitter request");
		}
		
		// obtain the authorization code
		String response = ((OAuth1ServiceImpl) this.service).getAuthorizationResponse(token, verifier, redirectURL);
		if(AssertUtils.isEmpty(response)) {
			return null;
		}
		
		Map<String, String> map = new UrlParamExtractor().extractTokens(response);
    	return new BaseAuthenticatedUser(map.get("oauth_token"), map.get("oauth_token_secret"), "", 3600, this);
	}
	
	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		return null;
	}

}
