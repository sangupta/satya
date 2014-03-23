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

import com.sangupta.jerry.util.AssertUtils;

/**
 * Enumeration that represents various authentication providers
 * available in the Satya framework.
 * 
 * @author sangupta
 * @since 1.0
 */
public enum AuthProvider {
	
	Google,
	
	Yahoo,
	
	Hotmail,
	
	Facebook,
	
	LinkedIn,
	
	FourSquare,
	
	DropBox,
	
	WindowsLive,
	
	MySpace,
	
	Yammer,
	
	SalesForce,
	
	Instagram,
	
	Twitter,
	
	Github;
	
	/**
	 * Parse the string expression and convert it into an {@link AuthProvider}
	 * instance.
	 * 
	 * @param provider
	 *            the string based representation of the provider
	 * 
	 * @return the {@link AuthProvider} instance created after parsing
	 */
	public static AuthProvider fromString(String provider) {
		if(AssertUtils.isBlank(provider)) {
			return null;
		}
		
		if("google".equalsIgnoreCase(provider)) {
			return Google;
		}
		
		if("yahoo".equalsIgnoreCase(provider)) {
			return Yahoo;
		}
		
		if("hotmail".equalsIgnoreCase(provider)) {
			return Hotmail;
		}
		
		if("facebook".equalsIgnoreCase(provider)) {
			return Facebook;
		}
		
		if("linkedin".equalsIgnoreCase(provider)) {
			return LinkedIn;
		}
		
		if("foursquare".equalsIgnoreCase(provider)) {
			return FourSquare;
		}
		
		if("dropbox".equalsIgnoreCase(provider)) {
			return DropBox;
		}
		
		if("windowslive".equalsIgnoreCase(provider)) {
			return WindowsLive;
		}
		
		if("myspace".equalsIgnoreCase(provider)) {
			return MySpace;
		}
		
		if("yammer".equalsIgnoreCase(provider)) {
			return Yammer;
		}
		
		if("salesforce".equalsIgnoreCase(provider)) {
			return SalesForce;
		}
		
		if("instagram".equalsIgnoreCase(provider)) {
			return Instagram;
		}
		
		if("twitter".equalsIgnoreCase(provider)) {
			return Twitter;
		}
		
		if("github".equalsIgnoreCase(provider)) {
			return Github;
		}
		
		throw new IllegalArgumentException("Unknown Authprovider: The value does not match any known provider");
	}

}
