package com.spring.rocketfood.mypage.wish.service;

import com.spring.rocketfood.mypage.wish.vo.WishGoodsVO;

public interface WishService {
	public int findWishId(String member_id) throws Exception;
	public boolean findWish(String member_id) throws Exception;
	public boolean findWishGoods(WishGoodsVO wishGoodsVO) throws Exception;
	public void addMemberIdInWish(String member_id) throws Exception;
	public void addGoodsInWishGoods(WishGoodsVO wishGoodsVO) throws Exception;
}
