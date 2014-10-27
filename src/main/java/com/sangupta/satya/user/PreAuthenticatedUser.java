package com.sangupta.satya.user;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.satya.AuthProvider;
import com.sangupta.satya.SatyaUtils;
import com.sangupta.satya.UserProfile;
import com.sangupta.satya.client.impl.TwitterAuthClient;
import com.sangupta.satya.user.impl.TwitterUserProfile;

public class PreAuthenticatedUser extends BaseAuthenticatedUser {
	
	public PreAuthenticatedUser(AuthProvider provider, String apiKey, String apiSecret, String userToken, String userSecret) {
		super(new TwitterAuthClient(new KeySecretPair(apiKey, apiSecret), SatyaUtils.EMPTY_STRING_LIST), new KeySecretPair(userToken, userSecret));
	}

	@Override
	public String getUserProfileURL() {
		return "https://api.twitter.com/1.1/account/verify_credentials.json";
	}

	@Override
	public Class<? extends UserProfile> getUserProfileClass() {
		return TwitterUserProfile.class;
	}

}
