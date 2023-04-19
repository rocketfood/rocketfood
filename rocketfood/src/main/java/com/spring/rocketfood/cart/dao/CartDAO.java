package com.spring.rocketfood.cart.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.rocketfood.cart.vo.CartGoodsVO;
import com.spring.rocketfood.cart.vo.CartVO;
import com.spring.rocketfood.goods.vo.GoodsVO;

public interface CartDAO {
	public List<CartGoodsVO> selectCartList(CartVO cartVO) throws DataAccessException;
	public List<GoodsVO> selectGoodsList(List<CartGoodsVO> cartList) throws DataAccessException;
	public CartVO selectCartInfo(int cart_id) throws DataAccessException;
	public boolean selectCountInCart(Map<String, Object> cartGoodsMap) throws DataAccessException;
	public void insertGoodsInCart(CartGoodsVO cartGoodsVO) throws DataAccessException;
	public int updateCartGoodsQty(CartGoodsVO cartGoodsVO) throws DataAccessException;
	public void deleteCartGoods(Map<String, Object> cartGoodsMap) throws DataAccessException;
	public int findCartId(String member_id) throws DataAccessException;
}
