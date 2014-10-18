/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: TokenType.java  
 * @Package:com.chengjf.wtfdoc.bean.token  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 上午11:28:30  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.bean.token;

/**
 * 标识类型
 * 
 * @ClassName: TokenType
 * @author: chengjf
 * @date: 2014-10-18
 */
public class TokenType {

	/**
	 * 标识类型key
	 * 
	 * @Fields: key
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private int key;

	/**
	 * 标识类型名称
	 * 
	 * @Fields: typeName
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private String typeName;

	/**
	 * @Title: getTypeName
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @Title: setTypeName
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

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

}
