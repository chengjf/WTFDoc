/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: Index.java  
 * @Package:com.chengjf.wtfdoc.parser  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-16 上午9:58:10  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.bean.index;

import com.chengjf.wtfdoc.common.EntryType;

/**
 * 索引
 * 
 * @ClassName: Index
 * @author: chengjf
 * @date: 2014-10-18
 */
public class Index {

	/**
	 * 索引名字，匹配的时候使用该字段
	 * 
	 * @Fields: name
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private String name;

	/**
	 * 索引类型
	 * 
	 * @Fields: type
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private EntryType type;

	/**
	 * 文件URL
	 * 
	 * @Fields: url
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private String url;

	/**
	 * @Title: getName
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @Title: setName
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @Title: getType
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public EntryType getType() {
		return type;
	}

	/**
	 * @Title: setType
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param type
	 */
	public void setType(EntryType type) {
		this.type = type;
	}

	/**
	 * @Title: getUrl
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @Title: setUrl
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
