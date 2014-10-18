/*    
 * Copyright 2010 digital china financial software Inc.
 * All rights reserved.
 * project name: iBatisLearn
 * version:  iBatisLearnV1.0          
 *---------------------------------------------------
 * @FileName: DatabaseAccess.java  
 * @Package:com.chengjf.ibatislearn.db  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-14 上午9:51:47  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.db;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapConfigParser;

/**
 * 数据库访问服务
 * 
 * @ClassName: DatabaseAccess
 * @author: chengjf
 * @date: 2014-10-18
 */
public class DatabaseAccess {

	/**
	 * sqlmap配置解析
	 * 
	 * @Fields: parser
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static SqlMapConfigParser parser;

	/**
	 * 数据库操作
	 * 
	 * @Fields: client
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static SqlMapClient client;

	/**
	 * sqlmap配置文件路径
	 * 
	 * @Fields: configPath
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static String configPath = "db/sqlMapConfig.xml";

	/**
	 * 获取SqlMapClient
	 * 
	 * @Title: getSqlMapClient
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static SqlMapClient getSqlMapClient() {
		if (client == null) {
			synchronized (DatabaseAccess.class) {
				if (client == null) {
					initSqlMapClient();
				}
			}
		}
		return client;
	}

	/**
	 * 初始化SqlMapClient
	 * 
	 * @Title: initSqlMapClient
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static void initSqlMapClient() {
		try {
			parser = new SqlMapConfigParser();
			Reader reader = Resources.getResourceAsReader(configPath);
			client = parser.parse(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
