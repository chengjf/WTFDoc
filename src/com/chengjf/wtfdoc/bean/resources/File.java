/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: Files.java  
 * @Package:com.chengjf.wtfdoc.bean.resources  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 上午11:15:38  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.bean.resources;

/**
 * 文件实例
 * 
 * @ClassName: File
 * @author: chengjf
 * @date: 2014-10-18
 */
public class File {

	/**
	 * 文件的Key，唯一标识
	 * 
	 * @Fields: key
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private int key;

	/**
	 * 文件的内容
	 * 
	 * @Fields: content
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private Object content;

	/**
	 * @Title: getKey
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @Title: setKey
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param key
	 */
	public void setKey(int key) {
		this.key = key;
	}

	/**
	 * @Title: getContent
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * @Title: setContent
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param content
	 */
	public void setContent(Object content) {
		this.content = content;
	}

}
