package com.sangupta.satya;

import java.security.Principal;

/**
 * Contract for all OAuth implementations that return a user profile from the
 * {@link AuthenticatedUser}. Contains detailed information on the user. This
 * also implements the {@link Principal} interface so that the obtained user can
 * directly be added to the <code>SecurityContext</code> implementations of Java
 * security frameworks.
 * 
 * @author sangupta
 * @since 1.0
 */
public interface UserProfile extends Principal {
	
	public String getUserID();
	
	public String getEmail();
	
	public String getDisplayName();
	
	public String getProfileLink();
	
	public String getProfileImageURL();
	
	public AuthProvider getAuthProvider();

}
