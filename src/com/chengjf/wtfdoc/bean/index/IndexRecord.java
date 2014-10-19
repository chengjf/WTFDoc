/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version: WTFDocV1.0
 *---------------------------------------------------
 * 
 * @FileName: IndexRecord.java
 * @Package:com.chengjf.wtfdoc.bean.index
 * @author: chengjf
 * @date: 2014-10-19
 * @version: V1.0
 */
package com.chengjf.wtfdoc.bean.index;

/**
 * @ClassName: IndexRecord
 * @author: chengjf
 * @date: 2014-10-19
 */
public class IndexRecord {

	/**
	 * @Fields: name
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	private String name;
	/**
	 * @Fields: url
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	private String url;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
