/**   
 * Copyright © 2016 酷玛. All rights reserved.
 * 
 * @Title: ShopDao.java 
 * @Prject: kmwz
 * @Package: com.dao 
 * @Description: TODO
 * @author: lhq   
 * @date: Aug 3, 2016 1:01:12 AM 
 * @version: V1.0   
 */
package com.lhq.ssm2.IDao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopDao
 * @Description: TODO
 * @author: lhq
 * @date: Aug 3, 2016 1:01:12 AM
 */
public interface TableZzSrcDao {

	public List<Map<String, Object>> getShopByDate(Map<String, Object> map);

	public List<Map<String, Object>> getShopClick(Map<String, Object> map);

	public List<Map<String, Object>> getShopWapOrPc(Map<String, Object> map);

	public void insertShopInfo(Map<String, Object> map);

}
