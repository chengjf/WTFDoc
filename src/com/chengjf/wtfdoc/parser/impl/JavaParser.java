/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: JavaParser.java  
 * @Package:com.chengjf.wtfdoc.parser.impl  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-16 上午10:00:36  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.parser.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.common.EntryType;
import com.chengjf.wtfdoc.common.PatternCommonConstants;
import com.chengjf.wtfdoc.parser.IParser;

/**
 * JavaDoc解析类
 * 
 * @ClassName: JavaParser
 * @author: chengjf
 * @date: 2014-10-18
 */
public class JavaParser implements IParser {

	/*
	 * (non-Javadoc)
	 * 
	 * @param indexs
	 * 
	 * @param source
	 * 
	 * @see com.chengjf.wtfdoc.parser.IParser#index(java.util.List,
	 * java.lang.String)
	 */
	@Override
	public void index(List<Index> indexs, String source, String api) {

		if (indexs == null) {
			indexs = new ArrayList<Index>();
		}
		Pattern pattern = PatternCommonConstants.getIndexRegex1();
		Matcher m = pattern.matcher(source);
		while (m.find()) {
			String name = PatternCommonConstants.getMethodFullName(m.group(1));
			if (name == null || name.equals("")) {
				continue;
			}
			Index index = new Index();
			index.setName(name);
			if (PatternCommonConstants.isMethod(name)) {
				index.setType(EntryType.Method.toString());
			} else {
				index.setType(EntryType.Field.toString());
			}
			index.setUrl(m.group(1));
			index.setParent(getParent(index.getUrl()));
			index.setApi(api);
			indexs.add(index);
		}
		if (indexs.size() == 0) {
			pattern = PatternCommonConstants.getIndexRegex2();
			m = pattern.matcher(source);
			while (m.find()) {
				String name = PatternCommonConstants.getMethodFullName(m
						.group(1));
				if (name == null || name.equals("")) {
					continue;
				}
				Index index = new Index();
				index.setName(name);
				if (PatternCommonConstants.isMethod(name)) {
					index.setType(EntryType.Method.toString());
				} else {
					index.setType(EntryType.Field.toString());
				}
				index.setUrl(m.group(1));
				index.setParent(getParent(index.getUrl()));
				index.setApi(api);
				indexs.add(index);
			}
		}
	}

	private String getParent(String url) {
		Pattern pattern = PatternCommonConstants.getParentRegex();
		Matcher m = pattern.matcher(url);
		while (m.find()) {
			String rs = m.group();
			rs = rs.substring(1, rs.lastIndexOf("."));
			return rs;
		}
		return "";
	}

}
