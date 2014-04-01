package com.sangupta.satya.client.impl;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.satya.AuthenticatedUser;
import com.sangupta.satya.client.BaseAuthClient;
import com.sangupta.satya.user.UserProfile;
import com.sangupta.jerry.oauth.service.impl.TwitterOAuthServiceImpl;

public class TwitterAuthClient extends BaseAuthClient {
	
	public TwitterAuthClient(KeySecretPair pair) {
		super(new TwitterOAuthServiceImpl(pair), "");
	}

	@Override
	public AuthenticatedUser verifyUser(HttpServletRequest request,	String redirectURL) {
		return null;
	}

	@Override
	public UserProfile getUserProfile(KeySecretPair accessPair) {
		return null;
	}

}
