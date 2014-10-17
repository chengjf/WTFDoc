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
 * @ClassName: Files
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-15 上午11:15:38
 */
public class File {

	private int key;
	private Object content;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
