/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: SqlMapCommonConstants.java  
 * @Package:com.chengjf.wtfdoc.dao  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 下午12:50:12  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.common;

/**
 * 数据库Sql语句ID常量
 * 
 * @ClassName: SqlMapCommonConstants
 * @author: chengjf
 * @date: 2014-10-18
 */
public class SqlMapCommonConstants {
	/**
	 * @Fields: QUERY_ALL_FILEPATHS
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	public static final String QUERY_ALL_FILEPATHS = "queryAllFilePaths";
	/**
	 * @Fields: QUERY_ALL_FILES
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	public static final String QUERY_ALL_FILES = "queryAllFiles";
	/**
	 * @Fields: QUERY__FILE_BY_KEY
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	public static final String QUERY__FILE_BY_KEY = "queryFileByKey";

	// /////////
	// Index //
	// /////////

	/**
	 * @Fields: CREATE_INDEX_TABLE
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	public static final String CREATE_INDEX_TABLE = "createIndexTable";

	/**
	 * @Fields: QUERY_ALL_INDEXS
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	public static final String QUERY_ALL_INDEXS = "queryAllIndexs";

	/**
	 * @Fields: INSERT_INDEX
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	public static final String INSERT_INDEX = "insertIndex";

	// ///////////////
	// IndexRecord //
	// ///////////////

	/**
	 * @Fields: CREATE_INDEXRECORD_TABLE
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	public static final String CREATE_INDEXRECORD_TABLE = "createIndexRecordTable";

	/**
	 * @Fields: QUERY_ALL_INDEXRECORDS
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	public static final String QUERY_ALL_INDEXRECORDS = "queryAllIndexRecords";

	/**
	 * @Fields: INSERT_INDEXRECORD
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	public static final String INSERT_INDEXRECORD = "insertIndexRecord";

}