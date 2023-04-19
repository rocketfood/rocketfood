package com.spring.rocketfood.goods.service;

import java.util.List;
import java.util.Map;

import com.spring.rocketfood.goods.vo.GoodsVO;

public interface GoodsService {
	
	public List<GoodsVO> listGoodsByCategory(Map<String, Object> goodsMap) throws Exception;
	public Map<String,List<GoodsVO>> listGoodsByStatus() throws Exception;
	public Map<String, Object> goodsDetail(int goods_id) throws Exception;	
	public List<String> keywordSearch(String keyword) throws Exception;
	public List<GoodsVO> searchGoods(String searchWord) throws Exception;
}
