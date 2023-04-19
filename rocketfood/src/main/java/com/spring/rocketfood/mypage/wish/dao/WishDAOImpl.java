package com.spring.rocketfood.mypage.wish.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.rocketfood.mypage.wish.vo.WishGoodsVO;

@Repository("wishDAO")
public class WishDAOImpl implements WishDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//member_id로 wish_id 가져오기     
	@Override
	public int selectWishId(String member_id) throws DataAccessException {		
		int wish_id = sqlSession.selectOne("mapper.mypage.selectWishId", member_id);
		return wish_id;
	}
	
	//wish에서 member_id가 이미 존재하는지
	@Override
	public boolean selectCountInwish(String member_id) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.mypage.selectCountInWish", member_id);
		return Boolean.parseBoolean(result);
	}
	
	//wishGoods에 상품이 이미 존재하는지
	@Override
	public boolean selectCountInWishGoods(WishGoodsVO wishGoodsVO) throws DataAccessException {
		String  result = sqlSession.selectOne("mapper.mypage.selectCountInWishGoods", wishGoodsVO);
		return Boolean.parseBoolean(result);
	}

	//wish에 member_id 담기
	@Override
	public void insertMemberIdInWish(String member_id) throws DataAccessException {
		sqlSession.insert("mapper.mypage.insertMemberIdInWish", member_id);
	}
	
	//wishGoods에 상품 담기
	@Override
	public void insertGoodsInWishGoods(WishGoodsVO wishGoodsVO) throws DataAccessException {
		sqlSession.insert("mapper.mypage.insertGoodsInWishGoods", wishGoodsVO);
	}

}
