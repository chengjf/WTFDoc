/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: IParser.java  
 * @Package:com.chengjf.wtfdoc.parser  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-16 上午9:43:31  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.parser;

import java.util.List;

import com.chengjf.wtfdoc.bean.index.Index;

/**
 * 解析接口
 * 
 * @ClassName: IParser
 * @author: chengjf
 * @date: 2014-10-18
 */
public interface IParser {

	/**
	 * @Title: index
	 * @author: chengjf
	 * @date: 2014-10-20
	 * @param indexs
	 * @param source
	 * @param api
	 */
	void index(List<Index> indexs, String source, String api);
}
