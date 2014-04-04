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

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.satya.client.AuthClient;
import com.sangupta.satya.client.impl.FacebookAuthClient;
import com.sangupta.satya.client.impl.GoogleAuthClient;
import com.sangupta.satya.client.impl.MicrosoftLiveAuthClient;
import com.sangupta.satya.client.impl.TwitterAuthClient;
import com.sangupta.satya.client.impl.YahooAuthClient;

/**
 * The centralized authentication manager in the Satya authentication framework.
 * 
 * @author sangupta
 * @since 1.0
 */
public final class AuthManager {
	
	/**
	 * A list of all initialized authentication clients
	 */
	private static final Map<AuthProvider, AuthClient> AUTH_CLIENTS = new HashMap<AuthProvider, AuthClient>();
	
	/**
	 * Prevent from instances being constructed in standard programming
	 * constructs
	 */
	private AuthManager() {
		
	}

	/**
	 * Load all providers based on the given {@link AuthConfig} instance
	 * 
	 * @param authConfig
	 *            the authentication configuration instance to use for
	 *            initialization
	 * 
	 * @throws IllegalArgumentException
	 *             if {@link AuthConfig} instance is <code>null</code>
	 * 
	 * @throws IllegalArgumentException
	 *             if there are no {@link AuthProvider}s added to the
	 *             {@link AuthConfig} instance
	 * 
	 */
	public static void loadConfig(AuthConfig authConfig) {
		if(authConfig == null) {
			throw new IllegalArgumentException("AuthConfig cannot be null");
		}
		
		Set<AuthProvider> providers = authConfig.getProviders();
		if(AssertUtils.isEmpty(providers)) {
			throw new IllegalArgumentException("No providers are configured in AuthConfig");
		}
		
		for(AuthProvider provider : providers) {
			KeySecretPair pair = authConfig.getConfig(provider);
			
			switch(provider) {
				case Google:
					AUTH_CLIENTS.put(provider, new GoogleAuthClient(pair));
					continue;
					
				case Facebook:
					AUTH_CLIENTS.put(provider, new FacebookAuthClient(pair));
					continue;
					
				case Twitter:
					AUTH_CLIENTS.put(provider, new TwitterAuthClient(pair));
					continue;
					
				case Yahoo:
					AUTH_CLIENTS.put(provider, new YahooAuthClient(pair));
					continue;
					
				case MicrosoftLive:
					AUTH_CLIENTS.put(provider, new MicrosoftLiveAuthClient(pair));
					continue;
					
				default:
					// TODO: do nothing for now - change to throw exception once complete
					break;
			}
		}
	}
	
	/**
	 * Get redirect URL for the given authentication provider, given the
	 * callback and the permissions we need to call with. It is recommended to
	 * use the
	 * {@link AuthManager#authenticateUser(AuthProvider, String, String)} method
	 * for better type safety.
	 * 
	 * @param provider
	 *            a case-insensitive representation of the authentication
	 *            provider.
	 * 
	 * @param permissions
	 *            the {@link AuthPermissions} which are needed
	 * 
	 * @param callbackURL
	 *            the callback URL at which the OAuth server will call us back
	 * 
	 * @return the login URL to which a user may be redirected, or asked to open
	 *         in a browser to complete the authentication workflow
	 * 
	 * @throws IllegalArgumentException
	 *             if the {@link AuthProvider} or {@link AuthPermissions} is
	 *             <code>null</code>, or if the callback URL is
	 *             <code>null/empty</code>
	 */
	public static String getAuthRedirectURL(String provider, AuthPermissions permissions, String callbackURL) {
		AuthProvider authProvider = AuthProvider.fromString(provider);
		return getAuthRedirectURL(authProvider, permissions, callbackURL);
	}
	
	/**
	 * Get redirect URL for the given authentication provider, given the
	 * callback and the permissions we need to call with.
	 * 
	 * @param provider
	 *            the {@link AuthProvider} for which authentication URL is being
	 *            seeked
	 * 
	 * @param permissions
	 *            the {@link AuthPermissions} which are needed
	 * 
	 * @param callbackURL
	 *            the callback URL at which the OAuth server will call us back
	 * 
	 * @return the login URL to which a user may be redirected, or asked to open
	 *         in a browser to complete the authentication workflow
	 * 
	 * @throws IllegalArgumentException
	 *             if the {@link AuthProvider} or {@link AuthPermissions} is
	 *             <code>null</code>, or if the callback URL is
	 *             <code>null/empty</code>
	 */
	public static String getAuthRedirectURL(AuthProvider provider, AuthPermissions permissions, String callbackURL) {
		if(provider == null) {
			throw new IllegalArgumentException("Authentication provider cannot be null");
		}
		
		if(permissions == null) {
			throw new IllegalArgumentException("Authentication permissions cannot be null");
		}
		
		if(AssertUtils.isEmpty(callbackURL)) {
			throw new IllegalArgumentException("Callback URL cannot be null/empty");
		}
		
		// get the client
		AuthClient client = AUTH_CLIENTS.get(provider);
		if(client == null) {
			throw new IllegalArgumentException("Authentication provider not available in the system");
		}
		
		return client.getLoginRedirectURL(callbackURL);
	}

	/**
	 * Authenticate the user from an incoming request.
	 * 
	 * @param request
	 *            the {@link HttpServletRequest} that was received after
	 *            authentication from the OAuth server
	 * 
	 * @param redirectURL
	 *            the redirect URL used for authentication
	 * 
	 * @return an instance of {@link AuthenticatedUser} that can then make calls
	 *         to the OAuth server on behalf of the user
	 * 
	 * @throws AssertionError
	 *             if we are unable to detect the {@link AuthClient} from the
	 *             provided {@link HttpServletRequest}
	 */
	public static AuthenticatedUser authenticateUser(HttpServletRequest request, String redirectURL) {
		// decipher the authprovider based on the request
		AuthClient client = decipherAuthClientFromRequest(request);
		if(client == null) {
			throw new AssertionError("Unable to detect the authentication client from the callback that was received");
		}
		
		return client.verifyUser(request, redirectURL);
	}
	
	/**
	 * Authenticate the user for the given {@link AuthProvider}, the auth token
	 * and the redirect URL that was supplied for authentication.
	 * 
	 * @param authProvider
	 *            the {@link AuthProvider} for which we are verifying the user
	 * 
	 * @param token
	 *            the authentication token as fetched from the OAuth server
	 * 
	 * @param redirectURL
	 *            the redirect URL used for authentication
	 * 
	 * @return an instance of {@link AuthenticatedUser} that can then make calls
	 *         to the OAuth server on behalf of the user
	 * 
	 * @throws IllegalArgumentException
	 *             if the given {@link AuthProvider} is <code>null</code>
	 */
	public static AuthenticatedUser authenticateUser(AuthProvider authProvider, String token, String redirectURL) {
		AuthClient client = AUTH_CLIENTS.get(authProvider);
		if(client == null) {
			throw new AssertionError("No authentication client configured for the given provider: " + authProvider);
		}
		
		return client.verifyUser(token, redirectURL);
	}

	/**
	 * Find from the request the {@link AuthClient} that should manage this callback.
	 * 
	 * @param request
	 * @return
	 */
	private static AuthClient decipherAuthClientFromRequest(HttpServletRequest request) {
		// TODO: fix this using the user-agent or otherwise
		if(AUTH_CLIENTS.size() == 1) {
			return AUTH_CLIENTS.get(AUTH_CLIENTS.keySet().iterator().next());
					
		}
		
		return AUTH_CLIENTS.get(AuthProvider.Yahoo);
	}
	
	/**
	 * Return the {@link AuthClient} for the given {@link AuthProvider}.
	 * 
	 * @param provider
	 *            the {@link AuthProvider} that we are need {@link AuthClient}
	 *            for
	 * 
	 * @return the associated auth client
	 * 
	 * @throws IllegalArgumentException
	 *             if the given {@link AuthProvider} is <code>null</code>
	 */
	public static AuthClient getAuthClient(AuthProvider provider) {
		if(provider == null) {
			throw new IllegalArgumentException("AuthProvider cannot be null");
		}
		
		return AUTH_CLIENTS.get(provider);
	}

	/**
	 * Shutdown the authentication manager. This basically clears all added
	 * {@link AuthClient}s from the cache and makes the slate clean for all
	 * {@link AuthManager}. However, any {@link AuthenticatedUser} that is being
	 * used elsewhere, will continue to function and use the previously
	 * configured {@link AuthClient} instance.
	 * 
	 */
	public static void shutDown() {
		AuthManager.AUTH_CLIENTS.clear();
	}
	
}
