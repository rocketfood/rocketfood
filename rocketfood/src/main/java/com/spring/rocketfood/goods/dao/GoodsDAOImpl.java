package com.spring.rocketfood.goods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.goods.vo.GoodsVO;
import com.spring.rocketfood.goods.vo.ImageFileVO;

@Repository("goodsDAO")
public class GoodsDAOImpl  implements GoodsDAO{
	@Autowired
	private SqlSession sqlSession;

	//카테고리별 상품 불러오기
	@Override
	public List<GoodsVO> selectGoodsListByCategory(Map<String, Object> goodsMap) throws DataAccessException {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		//1차 카테고리인지 2차 카테고리인지 확인
		int category_id = (Integer)goodsMap.get("category_id");
		int count = sqlSession.selectOne("mapper.admin.category.countCategory"); //1차 카테고리 수 불러오기
		
		if(category_id <= count) { //카테고리 아이디가 1차 카테고리 범위 내에 있다면
			
			//1차 카테고리의 하위 카테고리 아이디를 불러와서 해당 카테고리로 쿼리 실행 후 리스트에 담는다
			List<Integer> depth2CategoryId = sqlSession.selectList("mapper.admin.category.selectCategory2depth", category_id);
			list = sqlSession.selectList("mapper.goods.selectGoodsBy2depthList", depth2CategoryId);
			
		} else { // 2차 카테고리일 때
			list = sqlSession.selectList("mapper.goods.selectGoodsListByCategory", goodsMap);
		}

	   return list;	
     
	}
	
	//상태별 상품 불러오기
	@Override
	public List<GoodsVO> selectGoodsList(String goods_status) throws DataAccessException {
		List<GoodsVO> goodsList = sqlSession.selectList("mapper.goods.selectGoodsListByStatus", goods_status);
	   return goodsList;	
     
	}
		
	//검색어로 상품 불러오기
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
	   List<String> list = sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);
	   
	   return list;
	}
	
	//검색어로 상품 불러오기
	@Override
	public List<GoodsVO> selectGoodsBySearchWord(String searchWord) throws DataAccessException{
		List<GoodsVO> list = sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",searchWord);
		System.out.println("goodsDAO selectGoodsBySearchWord : " + list.size());
		 return list;
	}
	
	//상품 상세 불러오기
	@Override
	public GoodsVO selectGoodsDetail(int goods_id) throws DataAccessException{
		GoodsVO goodsVO=(GoodsVO)sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
		return goodsVO;
	}
	
	//상품 이미지 불러오기 
	@Override
	public List<ImageFileVO> selectGoodsDetailImage(int goods_id) throws DataAccessException{
		List<ImageFileVO> imageList = sqlSession.selectList("mapper.goods.selectGoodsDetailImage", goods_id);
		return imageList;
	}
	
}
