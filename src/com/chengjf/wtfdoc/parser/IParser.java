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
	 * 将传入的source进行解析，将解析生成的索引放入indexs中
	 * 
	 * @Title: index
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param indexs
	 * @param source
	 */
	public void index(List<Index> indexs, String source);
}
