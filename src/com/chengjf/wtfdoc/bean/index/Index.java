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
 * @ClassName: Index
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-16 上午9:58:10
 */
public class Index {

	private String name;
	private EntryType type;
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntryType getType() {
		return type;
	}

	public void setType(EntryType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
