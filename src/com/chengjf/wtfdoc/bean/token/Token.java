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
 * @ClassName: Token
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-15 上午11:30:14
 */
public class Token {
	private int key;
	private int tokenType;
	private String tokenName;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getTokenType() {
		return tokenType;
	}

	public void setTokenType(int tokenType) {
		this.tokenType = tokenType;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}
}
