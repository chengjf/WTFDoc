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
	 * 该索引所属的文件
	 * 
	 * @Fields: parent
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	private String parent;

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
//	public void setType(EntryType type) {
//		this.type = type;
//	}

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

	/**
	 * @Title: getParent
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @return
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * @Title: setParent
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @param parent
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 * 便于从数据库中直接进行映射操作
	 * 
	 * @Title: setType
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @param type
	 */
	public void setType(String type) {
		this.type = EntryType.valueOf(type);
	}

}
