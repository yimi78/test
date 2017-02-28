package com.lhq.ssm2.service;

import java.util.List;
import java.util.Map;

public interface ShopOptimizeService {

	 public String initParam( String beginDate , String endDate , String shopId , String taskId ,
				Map<String , Object> param ) ;
	 
	 public List<Map<String , Object>> getShopByDate( Map<String , Object> map ) ;
	 
	 public List<Map<String , Object>> merge( List<Map<String , Object>> shopInfo ,
				Map<String , Map<String , String>> usersInfoMap , List<String> adCol ) ;
	 
	 public List<Map<String , Object>> check( List<Map<String , Object>> list ) ;
}
