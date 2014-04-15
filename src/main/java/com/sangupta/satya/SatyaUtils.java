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

import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.StringUtils;

/**
 * @author sangupta
 *
 */
public class SatyaUtils {

	public static final String[] EMPTY_STRING_LIST = new String[] { };

	public static String merge(String[] items, char ch) {
		if(AssertUtils.isEmpty(items)) {
			return StringUtils.EMPTY_STRING;
		}
		
		StringBuilder builder = new StringBuilder(1024);
		for(int index = 0; index < items.length; index++) {
			if(index > 0) {
				builder.append(ch);
			}
			
			builder.append(items[index]);
		}
		
		return builder.toString();
	}
}
