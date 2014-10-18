/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: FilePaths.java  
 * @Package:com.chengjf.wtfdoc.bean.resources  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 上午11:21:19  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.bean.resources;

/**
 * 文件路径
 * 
 * @ClassName: FilePath
 * @author: chengjf
 * @date: 2014-10-18
 */
public class FilePath {

	/**
	 * 文件的key
	 * 
	 * @Fields: key
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private int key;

	/**
	 * 文件的路径
	 * 
	 * @Fields: path
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private String path;

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
	 * @Title: getPath
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @Title: setPath
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
