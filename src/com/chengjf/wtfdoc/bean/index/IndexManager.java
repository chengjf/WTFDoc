/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: IndexManager.java  
 * @Package:com.chengjf.wtfdoc.bean.index  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-16 上午10:20:54  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.bean.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 索引管理器
 * 不同的API文档都有自己的一套索引，使用该管理器进行管理
 * 
 * @ClassName: IndexManager
 * @author: chengjf
 * @date: 2014-10-18
 */
public class IndexManager {

	/**
	 * 索引的内部存储
	 * 
	 * @Fields: maps
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private Map<String, List<Index>> maps;

	/**
	 * 索引管理器的实例
	 * 
	 * @Fields: instance
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static IndexManager instance;

	/**
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private IndexManager() {
		this.maps = new HashMap<String, List<Index>>();
	}

	/**
	 * 获取索引管理器的实例
	 * 
	 * @Title: getIndexManager
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static IndexManager getIndexManager() {
		if (instance == null) {
			synchronized (IndexManager.class) {
				if (instance == null) {
					instance = new IndexManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 添加索引
	 * 
	 * @Title: addIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param namespace
	 * @param indexs
	 */
	public void addIndexs(String namespace, List<Index> indexs) {
		this.maps.put(namespace, indexs);
	}

	/**
	 * 删除索引 该方法会删除给定API下的所有索引
	 * 
	 * @Title: removeIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param namespace
	 */
	public void removeIndexs(String namespace) {
		this.maps.remove(namespace);
	}

	/**
	 * 获取索引
	 * 
	 * @Title: getIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param namespace
	 * @return
	 */
	public List<Index> getIndexs(String namespace) {
		return this.maps.get(namespace);
	}
}
