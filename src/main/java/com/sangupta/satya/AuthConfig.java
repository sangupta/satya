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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sangupta.jerry.oauth.domain.KeySecretPair;

/**
 * 
 * @author sangupta
 *
 */
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
