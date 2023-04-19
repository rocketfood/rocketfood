package com.spring.rocketfood.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rocketfood.goods.dao.GoodsDAO;
import com.spring.rocketfood.goods.vo.GoodsVO;
import com.spring.rocketfood.goods.vo.ImageFileVO;

@Service("goodsService")
@Transactional(propagation=Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDAO goodsDAO;
	
	//카테고리별 상품 불러오기
	public List<GoodsVO> listGoodsByCategory(Map<String, Object> goodsMap) throws Exception {	
		List<GoodsVO> goodsList = goodsDAO.selectGoodsListByCategory(goodsMap);
		return goodsList;
	}
	
	//상태별 상품 불러오기
	public Map<String,List<GoodsVO>> listGoodsByStatus() throws Exception {
		Map<String,List<GoodsVO>> goodsMap = new HashMap<String,List<GoodsVO>>();
		
		List<GoodsVO> goodsList = goodsDAO.selectGoodsList("best");
		goodsMap.put("best", goodsList);
		
		goodsList = goodsDAO.selectGoodsList("new");
		goodsMap.put("new", goodsList);
		
		goodsList = goodsDAO.selectGoodsList("recommend");
		goodsMap.put("recommend", goodsList);
		return goodsMap;
	}
	
	//상품 상세 불러오기
	public Map<String, Object> goodsDetail(int goods_id) throws Exception {
		Map<String, Object> goodsMap = new HashMap<String, Object>();
		GoodsVO goodsVO = goodsDAO.selectGoodsDetail(goods_id);
		goodsMap.put("goodsVO", goodsVO);
		List<ImageFileVO> imageList = goodsDAO.selectGoodsDetailImage(goods_id);
		goodsMap.put("imageList", imageList);
		return goodsMap;
	}
	
	//검색어 불러오기
	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list = goodsDAO.selectKeywordSearch(keyword);
		return list;
	}
	
	//검색어로 상품 불러오기
	public List<GoodsVO> searchGoods(String searchWord) throws Exception{
		List<GoodsVO> goodsList = goodsDAO.selectGoodsBySearchWord(searchWord);
		return goodsList;
	}
	
	
}
