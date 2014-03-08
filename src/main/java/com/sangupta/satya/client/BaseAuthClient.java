package com.sangupta.satya.client;

import com.sangupta.jerry.oauth.service.OAuthService;

public abstract class BaseAuthClient implements AuthClient {
	
	/**
	 * The {@link OAuthService} to use
	 * 
	 */
	protected final OAuthService service;
	
	public BaseAuthClient(OAuthService service) {
		this.service = service;
	}
	
	@Override
	public boolean signOut() {
		return false;
	}

}
