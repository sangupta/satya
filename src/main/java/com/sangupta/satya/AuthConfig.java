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

package com.sangupta.satya;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sangupta.jerry.exceptions.NotImplementedException;
import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.util.AssertUtils;

/**
 * Holds central configuration for the Satya authentication system. This is
 * an instance class, so that multiple {@link AuthManager}s can use different
 * configuration depending on the application being developed.
 * 
 * @author sangupta
 * @since 1.0
 */
public class AuthConfig {

	/**
	 * Holds the configuration list that have been aded
	 * 
	 */
	private final Map<AuthProvider, AuthProviderConfiguration> config = new HashMap<AuthProvider, AuthProviderConfiguration>();
	
	/**
	 * Add a new {@link AuthProviderConfiguration} for the given {@link AuthProvider}.
	 * 
	 * @param authProvider
	 *            the authentication provider against which keys need to be
	 *            added
	 * 
	 * @param pair
	 *            the key-secret pair that needs to be added
	 * 
	 * @param scope
	 *            the default scope that needs to be used with the
	 *            authentication provider
	 * 
	 * @throws IllegalArgumentException
	 *             if the authProvider is null
	 * 
	 * @throws IllegalArgumentException
	 *             if the keySecretPair is null
	 */
	public void addConfig(AuthProvider authProvider, KeySecretPair keySecretPair, String scope) {
		addConfig(authProvider, keySecretPair, new String[] { scope });
	}
	
	/**
	 * Add a new {@link AuthProviderConfiguration} for the given {@link AuthProvider}.
	 * 
	 * @param authProvider
	 *            the authentication provider against which keys need to be
	 *            added
	 * 
	 * @param pair
	 *            the key-secret pair that needs to be added
	 * 
	 * @param scopes
	 *            the list of all default scopes that need to be used with the
	 *            authentication provider
	 * 
	 * @throws IllegalArgumentException
	 *             if the authProvider is null
	 * 
	 * @throws IllegalArgumentException
	 *             if the keySecretPair is null
	 */
	public void addConfig(AuthProvider authProvider, KeySecretPair keySecretPair, String... scopes) {
		if(authProvider == null) {
			throw new IllegalArgumentException("AuthProvider cannot be null");
		}
		
		if(keySecretPair == null) {
			throw new IllegalArgumentException("KeySecretPair cannot be null");
		}
		
		if(scopes == null) {
			throw new IllegalArgumentException("Default scopes cannot be null/empty");
		}
		
		this.config.put(authProvider, new AuthProviderConfiguration(keySecretPair, scopes));
	}

	/**
	 * Return the {@link AuthProviderConfiguration} instance associated with this {@link AuthProvider}.
	 * 
	 * @param provider
	 *            the provider for which the {@link KeySecretPair} is needed
	 * 
	 * @return the {@link AuthProviderConfiguration} if found, <code>null</code> otherwise
	 */
	public AuthProviderConfiguration getConfig(AuthProvider provider) {
		if(provider == null) {
			return null;
		}
		
		return config.get(provider);
	}
	
	/**
	 * Return the list of all auth providers in this {@link AuthConfig}
	 * instance.
	 * 
	 * @return the list of all {@link AuthProvider}s configured
	 */
	public Set<AuthProvider> getProviders() {
		return this.config.keySet();
	}
	
	/**
	 * Load the {@link AuthConfig} instance configuration using the given file.
	 * 
	 * @param filePath
	 *            the absolute file path from where to load the file
	 * 
	 */
	public void load(String filePath) {
		if(AssertUtils.isEmpty(filePath)) {
			throw new IllegalArgumentException("File path cannot be empty/null");
		}
		
		File file = new File(filePath);
		if(!file.exists()) {
			throw new IllegalArgumentException("File path cannot be empty/null");
		}
		
		if(!file.isFile()) {
			throw new IllegalArgumentException("File does not represent a valid file");
		}
		
		throw new NotImplementedException();
	}
	
}
