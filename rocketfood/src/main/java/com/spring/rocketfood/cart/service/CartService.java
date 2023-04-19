package com.spring.rocketfood.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.rocketfood.cart.vo.CartGoodsVO;
import com.spring.rocketfood.cart.vo.CartVO;
import com.spring.rocketfood.goods.vo.GoodsVO;

public interface CartService {
	public Map<String, Object> myCartList(CartVO cartVO) throws Exception;
	public boolean findCartGoods(Map<String, Object> cartGoodsMap) throws Exception;
	public CartVO findCartInfo(int cart_id) throws Exception;
	public void addGoodsInCart(CartGoodsVO cartGoodsVO) throws Exception;
	public boolean modifyCartQty(CartGoodsVO cartGoodsVO) throws Exception;
	public void removeCartGoods(Map<String, Object> cartGoodsMap) throws Exception;
	public int findCartId(String member_id) throws Exception;
}
