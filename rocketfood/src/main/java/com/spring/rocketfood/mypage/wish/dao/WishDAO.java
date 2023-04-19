package com.spring.rocketfood.mypage.wish.dao;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.mypage.wish.vo.WishGoodsVO;

public interface WishDAO {
	public int selectWishId(String member_id) throws DataAccessException;
	public boolean selectCountInwish(String member_id) throws DataAccessException;
	public boolean selectCountInWishGoods(WishGoodsVO wishGoodsVO) throws DataAccessException;
	public void insertMemberIdInWish(String member_id) throws DataAccessException;
	public void insertGoodsInWishGoods(WishGoodsVO wishGoodsVO) throws DataAccessException;
}
