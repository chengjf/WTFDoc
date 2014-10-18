/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: Token.java  
 * @Package:com.chengjf.wtfdoc.bean.token  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 上午11:30:14  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.bean.token;

/**
 * 标识
 * 
 * @ClassName: Token
 * @author: chengjf
 * @date: 2014-10-18
 */
public class Token {

	/**
	 * 标识key
	 * 
	 * @Fields: key
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private int key;

	/**
	 * 标识类型
	 * 
	 * @Fields: tokenType
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private int tokenType;

	/**
	 * 标识名称
	 * 
	 * @Fields: tokenName
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private String tokenName;

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
	 * @Title: getTokenType
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public int getTokenType() {
		return tokenType;
	}

	/**
	 * @Title: setTokenType
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param tokenType
	 */
	public void setTokenType(int tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @Title: getTokenName
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * @Title: setTokenName
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param tokenName
	 */
	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
}
