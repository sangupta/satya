package com.sangupta.satya.client.impl;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.oauth.extractor.JSONExtractor;
import com.sangupta.jerry.oauth.extractor.TokenExtractor;
import com.sangupta.jerry.oauth.scope.LinkedInScopes;
import com.sangupta.jerry.oauth.service.impl.LinkedInOAuthServiceImpl;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.UserProfile;

/**
 * 
 * @author sangupta
 *
 */
public class LinkedInAuthClient extends BaseAuthClient {
	
	public LinkedInAuthClient(KeySecretPair pair) {
		super(new LinkedInOAuthServiceImpl(pair), LinkedInScopes.BASIC_PROFILE);
	}

	@Override
	protected String getProviderName() {
		return "LinkedIn";
	}

	@Override
	protected TokenExtractor getTokenExtractor() {
		return JSONExtractor.INSTANCE;
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		return null;
	}

}
