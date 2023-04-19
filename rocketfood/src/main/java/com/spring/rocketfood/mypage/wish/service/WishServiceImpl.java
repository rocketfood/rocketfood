package com.spring.rocketfood.mypage.wish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rocketfood.mypage.wish.dao.WishDAO;
import com.spring.rocketfood.mypage.wish.vo.WishGoodsVO;

@Service("wishService")
@Transactional(propagation = Propagation.REQUIRED)
public class WishServiceImpl implements WishService {
	@Autowired
	private WishDAO wishDAO;
	
	// member_id로 wish_id 가져오기
	@Override
	public int findWishId(String member_id) throws Exception {
		return wishDAO.selectWishId(member_id);
	}
	
	// wish에서 member_id 이미 존재하는지
	@Override
	public boolean findWish(String member_id) throws Exception {
		return wishDAO.selectCountInwish(member_id);
	}
	
	// wishGoods에서 상품 이미 존재하는지
	@Override
	public boolean findWishGoods(WishGoodsVO wishGoodsVO) throws Exception {
		return wishDAO.selectCountInWishGoods(wishGoodsVO);
	}

	// wish에 member_id 담기
	@Override
	public void addMemberIdInWish(String member_id) throws Exception {
		wishDAO.insertMemberIdInWish(member_id);
	}
	
	// wishGoods에 상품 담기
	@Override
	public void addGoodsInWishGoods(WishGoodsVO wishGoodsVO) throws Exception {
		wishDAO.insertGoodsInWishGoods(wishGoodsVO);
	}
}
