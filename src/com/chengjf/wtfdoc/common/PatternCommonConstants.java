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
 * 正则表达式常量
 * 
 * @ClassName: PatternCommonConstants
 * @author: chengjf
 * @date: 2014-10-18
 */
public class PatternCommonConstants {

	/**
	 * @Title: getAnchorNameRegex
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static Pattern getAnchorNameRegex() {
		return Pattern.compile("<A NAME=\"([^\"]*)\"[^>]*>");
	}

	/**
	 * @Title: getIndexRegex
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static Pattern getIndexRegex() {
		return Pattern
				.compile("<DT><A HREF=\"([^\"]*)\"[^>]*><B>([^<]*)</B></A>");
	}

	/**
	 * @Title: getJavaClassRegex
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static Pattern getJavaClassRegex() {
		return Pattern
				.compile("<A HREF=\"(([^\"]*)\\.html)\"[^>]*>(?:<I>)?([^<]*)(?:</I>)?</A>");
	}

	/**
	 * @Title: getJavaFieldRegex
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static Pattern getJavaFieldRegex() {
		return Pattern
				.compile("<TD><CODE><B><A HREF=\"([^\"]*)\"[^>]*>([^<]*)</A>");
	}

	/**
	 * @Title: getIndexUrlRegex
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static Pattern getIndexUrlRegex() {
		return Pattern.compile("<A HREF=\"([^\"]*)\"[^>]*>");
	}

	/**
	 * @Title: getApiTitleRegex
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static Pattern getApiTitleRegex() {
		return Pattern.compile("<TITLE>([^<]*)</TITLE>");
	}

	/**
	 * @Title: isMethod
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param url
	 * @return
	 */
	public static boolean isMethod(String url) {
		return url.endsWith("(");
	}

	/**
	 * @Title: getMethodFullName
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param url
	 * @return
	 */
	public static String getMethodFullName(String url) {
		return StringUtils.substringAfter(url, "#");
	}
	
	/**
	 * 从URL中获取文件名
	 * 
	 * @Title: getParentRegex
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @return
	 */
	public static Pattern getParentRegex() {
		return Pattern.compile("/[^\n/]*\\.html");
	}
}
