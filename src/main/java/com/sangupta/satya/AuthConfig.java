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

	private final Map<AuthProvider, KeySecretPair> config = new HashMap<AuthProvider, KeySecretPair>();
	
	/**
	 * Add a new key-secret pair for the given provider.
	 * 
	 * @param authProvider
	 *            the authentication provider against which keys need to be
	 *            added
	 * 
	 * @param pair
	 *            the key-secret pair that needs to be added
	 * 
	 * @throws IllegalArgumentException
	 *             if the authProvider is null
	 * 
	 * @throws IllegalArgumentException
	 *             if the keySecretPair is null
	 */
	public void addConfig(AuthProvider authProvider, KeySecretPair keySecretPair) {
		if(authProvider == null) {
			throw new IllegalArgumentException("AuthProvider cannot be null");
		}
		
		if(keySecretPair == null) {
			throw new IllegalArgumentException("KeySecretPair cannot be null");
		}
		
		this.config.put(authProvider, keySecretPair);
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
