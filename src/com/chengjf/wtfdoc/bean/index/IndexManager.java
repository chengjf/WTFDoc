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
 * @ClassName: IndexManager
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-16 上午10:20:54
 */
public class IndexManager {

	private Map<String, List<Index>> maps;

	private static IndexManager instance;

	private IndexManager() {
		this.maps = new HashMap<String, List<Index>>();
	}

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

	public void addIndexs(String namespace, List<Index> indexs) {
		this.maps.put(namespace, indexs);
	}

	public void removeIndexs(String namespace) {
		this.maps.remove(namespace);
	}
	
	public List<Index> getIndexs(String namespace) {
		return this.maps.get(namespace);
	}
}
