package com.sangupta.satya;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sangupta.jerry.oauth.domain.KeySecretPair;

public class AuthConfig {

	private final Map<AuthProvider, KeySecretPair> config = new HashMap<AuthProvider, KeySecretPair>();
	
	/**
	 * Add a new key-secret pair for the given provider.
	 * 
	 * @param provider
	 * @param pair
	 */
	public void addConfig(AuthProvider provider, KeySecretPair pair) {
		if(provider == null) {
			throw new IllegalArgumentException("AuthProvider cannot be null");
		}
		
		if(pair == null) {
			throw new IllegalArgumentException("KeySecretPair cannot be null");
		}
		
		this.config.put(provider, pair);
	}

	/**
	 * Return the {@link KeySecretPair} associated with this provider.
	 * 
	 * @param provider
	 * @return
	 */
	public KeySecretPair getConfig(AuthProvider provider) {
		if(provider == null) {
			return null;
		}
		
		return config.get(provider);
	}
	
	/**
	 * Return the list of all auth providers in this {@link AuthConfig} instance.
	 * 
	 * @return
	 */
	public Set<AuthProvider> getProviders() {
		return this.config.keySet();
	}
	
	/**
	 * Load the {@link AuthConfig} instance configuration using the givne file.
	 * 
	 * @param filePath
	 */
	public void load(String filePath) {
		// TODO: change this to load details from file
		this.config.put(AuthProvider.Google, new KeySecretPair("opensource.brickred.com", "YC06FqhmCLWvtBg/O4W/aJfj"));
	}
	
}
