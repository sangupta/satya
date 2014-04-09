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
