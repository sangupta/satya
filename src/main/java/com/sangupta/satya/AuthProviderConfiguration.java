/*************************************************************************
 *
 * MultiPLX Confidential
 * _____________________
 *
 * Copyright (C) 2012-2014, MultiPLX Founders.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the 
 * property of MultiPLX and its founders. The intellectual and technical 
 * concepts contained herein are proprietary to the MultiPLX owners 
 * mentioned elsewhere, and may be covered by U.S. and Foreign Patents, 
 * patents in process, and are protected by trade secret or copyright law. 
 * Dissemination of this information or reproduction of this material is 
 * strictly forbidden unless prior written permission is obtained from 
 * all persons mentioned before. Please see project license for more 
 * details.
 *
 **************************************************************************/

package com.sangupta.satya;

import com.sangupta.jerry.oauth.domain.KeySecretPair;

/**
 * Value object to store configuration for a given {@link AuthProvider}.
 * 
 * @author sangupta
 *
 */
public class AuthProviderConfiguration {

	public final KeySecretPair keySecretPair;
	
	public final String[] scopes;
	
	public AuthProviderConfiguration(KeySecretPair keySecretPair, String... scopes) {
		this.keySecretPair = keySecretPair;
		this.scopes = scopes;
	}
	
}
