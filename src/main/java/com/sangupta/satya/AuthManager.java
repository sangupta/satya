package com.sangupta.satya;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.oauth.domain.KeySecretPair;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.satya.client.AuthClient;
import com.sangupta.satya.client.GoogleAuthClient;

public class AuthManager {
	
	private static final Map<AuthProvider, AuthClient> AUTH_CLIENTS = new HashMap<AuthProvider, AuthClient>();

	/**
	 * Load all providers based on the auth config that we have
	 * 
	 * @param authConfig
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
					return;
					
				default:
					// TODO: do nothing for now - change to throw exception once complete
					break;
			}
		}
	}
	
	/**
	 * 
	 * @param provider
	 * @param permissions
	 * @param callbackURL
	 * @return
	 */
	public static String getAuthRedirectURL(String provider, AuthPermissions permissions, String callbackURL) {
		AuthProvider authProvider = AuthProvider.valueOf(provider);
		return getAuthRedirectURL(authProvider, permissions, callbackURL);
	}
	
	/**
	 * Get redirect URL for the given authentication provider, given the callback and the permissions
	 * we need to call with.
	 * 
	 * @param provider
	 * @param permissions
	 * @param callbackURL
	 * @return
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
	 * @return
	 */
	public static AuthenticatedUser authenticateUser(HttpServletRequest request) {
		// decipher the authprovider based on the request
		AuthClient client = decipherAuthClientFromRequest(request);
		if(client == null) {
			throw new AssertionError("Unable to detect the authentication client from the callback that was received");
		}
		
		return client.verifyUser(request);
	}

	/**
	 * Find from the request the {@link AuthClient} that should manage this callback.
	 * 
	 * @param request
	 * @return
	 */
	private static AuthClient decipherAuthClientFromRequest(HttpServletRequest request) {
		return null;
	}

	/**
	 * Shutdown the authentication manager
	 * 
	 */
	public static void shutDown() {
		AuthManager.AUTH_CLIENTS.clear();
	}
}
