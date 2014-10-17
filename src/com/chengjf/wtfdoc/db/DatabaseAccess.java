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
 * @ClassName: DatabaseAccess
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-14 上午9:51:47
 */
public class DatabaseAccess {

	private static SqlMapConfigParser parser;
	private static SqlMapClient client;

	private static String configPath = "db/sqlMapConfig.xml";

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

	private static void initSqlMapClient() {
		try {
			parser = new SqlMapConfigParser();
			Reader reader = Resources.getResourceAsReader(configPath);
			client = parser.parse(reader);

//			java.sql.Types.VARCHAR
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SqlMapClient client = DatabaseAccess.getSqlMapClient();
	}

}
