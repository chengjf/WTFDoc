/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: PatternCommonConstant.java  
 * @Package:com.chengjf.wtfdoc.common  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-16 上午9:25:27  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.common;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: PatternCommonConstant
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-16 上午9:25:27
 */
public class PatternCommonConstants {
	public static Pattern getAnchorNameRegex() {
		return Pattern.compile("<A NAME=\"([^\"]*)\"[^>]*>");
	}

	public static Pattern getIndexRegex() {
		return Pattern
				.compile("<DT><A HREF=\"([^\"]*)\"[^>]*><B>([^<]*)</B></A>");
	}

	public static Pattern getJavaClassRegex() {
		return Pattern
				.compile("<A HREF=\"(([^\"]*)\\.html)\"[^>]*>(?:<I>)?([^<]*)(?:</I>)?</A>");
	}

	public static Pattern getJavaFieldRegex() {
		return Pattern
				.compile("<TD><CODE><B><A HREF=\"([^\"]*)\"[^>]*>([^<]*)</A>");
	}

	public static Pattern getIndexUrlRegex() {
		return Pattern.compile("<A HREF=\"([^\"]*)\"[^>]*>");
	}

	public static Pattern getApiTitleRegex() {
		return Pattern.compile("<TITLE>([^<]*)</TITLE>");
	}

	public static boolean isMethod(String url) {
		return url.endsWith("(");
	}

	public static String getMethodFullName(String url) {
		return StringUtils.substringAfter(url, "#");
	}
}
