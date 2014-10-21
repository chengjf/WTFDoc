/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version: WTFDocV1.0
 *---------------------------------------------------
 * 
 * @FileName: ParseManager.java
 * @Package:com.chengjf.wtfdoc.service
 * @author: chengjf
 * @date: 2014-10-21
 * @version: V1.0
 */
package com.chengjf.wtfdoc.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.IOUtils;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.parser.impl.JavaParser;

/**
 * 解析管理类
 * 
 * @ClassName: ParseManager
 * @author: chengjf
 * @date: 2014-10-21
 */
public class ParseManager {

	private final static String INDEX = "index-all.html";

	/**
	 * @Fields: instance
	 * @author: chengjf
	 * @date: 2014-10-21
	 */
	private static final ParseManager instance = new ParseManager();

	/**
	 * @author: chengjf
	 * @date: 2014-10-21
	 */
	private ParseManager() {

	}

	/**
	 * @Title: getInstance
	 * @author: chengjf
	 * @date: 2014-10-21
	 * @return
	 */
	public static ParseManager getInstance() {
		return instance;
	}

	/**
	 * 解析文件
	 * 
	 * @Title: parse
	 * @author: chengjf
	 * @date: 2014-10-21
	 * @param inputStream
	 */
	public void parse(File file) {
		String namespace = getNamespace(file.getName());
		JarFile jar = null;
		try {
			jar = new JarFile(file);
			for (Enumeration<JarEntry> enums = jar.entries(); enums
					.hasMoreElements();) {
				JarEntry entry = enums.nextElement();
				if (!entry.isDirectory()) {
					if (entry.getName().equals(INDEX)) {
						InputStream inputStream = jar.getInputStream(entry);
						String str = IOUtils.toString(inputStream);
						parseIndex(namespace, file.getAbsolutePath(), str);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (jar != null) {
				try {
					jar.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * sqlite table 不能有"-"和 "."
	 * 
	 * @Title: getNamespace
	 * @author: chengjf
	 * @date: 2014-10-20
	 * @param str
	 * @return
	 */
	private static String getNamespace(String str) {
		// - -> _
		str = str.replaceAll("-", "_");
		// remove javadoc
		str = str.substring(0, str.lastIndexOf("_"));

		str = str.replaceAll("\\.", "_");
		return str;
	}

	/**
	 * 解析并加入IndexManager
	 * 
	 * @Title: parseIndex
	 * @author: chengjf
	 * @date: 2014-10-20
	 * @param namespace
	 * @param str
	 */
	private static void parseIndex(String namespace, String url, String str) {
		JavaParser parser = new JavaParser();
		List<Index> list = new ArrayList<Index>();
		parser.index(list, str, namespace);
		IndexManager.getIndexManager().addIndexs(namespace, url, list);
	}
}
