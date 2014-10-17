/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: HTMLParser.java  
 * @Package:com.chengjf.wtfdoc.parser  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 下午5:17:13  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.common.PatternCommonConstants;
import com.chengjf.wtfdoc.parser.impl.JavaParser;

/**
 * @ClassName: HTMLParser
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-15 下午5:17:13
 */
public class HTMLParser {

	public static void main(String[] args) throws IOException {
		File input = new File(
				"E:/Code/jar-libs/gson/google-gson-2.2.4/gson-2.2.4-javadoc/index-all.html");
		JavaParser parser= new JavaParser();
		List<Index> list = new ArrayList<Index>();
		parser.index(list, FileUtils.readFileToString(input, Charset.defaultCharset().name()));
		for(Index index : list) {
			System.err.println(index.getName() + "___" + index.getUrl());
		}

	}

	private static void getIndexes(File resource) throws IOException {
		String str = FileUtils.readFileToString(resource, Charset.defaultCharset().name());
		Pattern p = PatternCommonConstants.getJavaClassRegex();
		Pattern px = PatternCommonConstants.getJavaFieldRegex();
		Pattern pxx = PatternCommonConstants.getIndexRegex();
//		getMatcher(p,str);
//		getMatcher(px,str);
		getMatcher(pxx,str);
	}

	private static void addIndex(Map<String, String> indexMaps, String text,
			String url) throws FileNotFoundException {
		System.err.println(PatternCommonConstants.getMethodFullName(url) + "      " + url);

	}

	private static Matcher getMatcher(Pattern pattern, String str)
			throws FileNotFoundException {
		Matcher m = pattern.matcher(str);
		Map<String, String> indexMaps = new HashMap<String, String>(1024);
		while (m.find()) {
			addIndex(indexMaps, m.group(2), m.group(1));
		}
		return m;
	}

}
