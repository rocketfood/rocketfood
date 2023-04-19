package com.spring.rocketfood.goods.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.goods.vo.GoodsVO;
import com.spring.rocketfood.goods.vo.ImageFileVO;

public interface GoodsDAO {
	public List<GoodsVO> selectGoodsListByCategory(Map<String, Object> goodsMap) throws DataAccessException;
	public List<GoodsVO> selectGoodsList(String goods_status) throws DataAccessException;
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException;
	public GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException;
	public List<ImageFileVO> selectGoodsDetailImage(int goods_id) throws DataAccessException;
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException;
}
